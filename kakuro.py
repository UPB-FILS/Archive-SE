"""
Exemple : 
board = [[Brick(), Brick(v=14), Brick(v=5), Brick(v=28), Brick(v=3), Brick(), Brick(), Brick(v=26), Brick(v=5), Brick(v=22)],
         [Brick(h=12), Blank(), Blank(), Blank(), Blank(), Brick(v=12,h=24), Blank(), Blank(), Blank(), Blank()],
         [Brick(h=23), Blank(), Blank(), Blank(), Blank(), Blank(), Brick(v=32,h=21), Blank(), Blank(), Blank()],
         [Brick(), Brick(v=7), Brick(v=39), Blank(), Brick(h=6), Blank(), Blank(), Blank(), Brick(v=24), Blank()],
         [Brick(h=20), Blank(), Blank(), Blank(), Brick(v=19,h=27), Blank(), Blank(), Blank(), Blank(), Brick(v=34)],
         [Brick(h=6), Blank(), Blank(), Brick(v=22,h=23), Blank(), Blank(), Blank(), Brick(v=13,h=15), Blank(), Blank()],
         [Brick(h=14), Blank(), Blank(), Blank(), Blank(), Brick(h=14), Blank(), Blank(), Blank(), Blank()],
         [Brick(), Brick(v=6,h=22), Blank(), Blank(), Blank(), Brick(v=4,h=16), Blank(), Blank(), Brick(v=17), Blank()],
         [Brick(h=21), Blank(), Blank(), Blank(), Brick(h=24), Blank(), Blank(), Blank(), Blank(), Blank()],
         [Brick(h=15), Blank(), Blank(), Blank(), Blank(), Blank(), Brick(h=20), Blank(), Blank(), Blank()]]

k = KakuroBoard(board)

for entries in k.solve():
    print '*** SOLUTION ***'
    str = ''
    for row in board:
        for col in row:
            if isinstance(col, Brick):
                str += '_ '
            else:
                str += '%s ' % entries[col.myID].possibleValues[0]
        str += '\n'
    print str
"""

import operator

"""_findAssignments(val, blankList, exclude=[])

    Given a value and a list of blanks, find all possible assignments of values
    to entries that satisfies this value. Exclude can be used to exclude certain
    possible values. In this case, it is used recursively to make sure that each
    possible number appears at most once in each sum."""
    
def _findAssignments(val, blankList, exclude=[]):


    # If we have a full sum, yield it.
    if val == 0 and len(blankList) == 0:
        yield []

    # If we are out of blanks to fill and val was not achieved, we failed.
    if len(blankList) == 0:
        return

    # If we have no possible value left for any remaining position, we failed.
    if not reduce(lambda x,y: x and y,
                  [filter(lambda x:x not in exclude, blank.possibleValues) for blank in blankList],
                  True):
        return

    # If we cannot even use the biggest guys to complete, stop.
    if sum([max([i for i in blank.possibleValues if i not in exclude]) for blank in blankList]) < val:
        return

    # If we cannot even use the smallest guys to complete, stop.
    if sum([min([i for i in blank.possibleValues if i not in exclude]) for blank in blankList]) > val:
        return

    # Otherwise, try to fill in the next position and reiterate.
    for candidate in [i for i in blankList[0].possibleValues if i not in exclude]:
        for s in _findAssignments(val-candidate, blankList[1:], exclude + [candidate]):
            yield [candidate] + s


    """Represents a sum in the board, i.e. the value of the sum and the iist of
    Blank objects contributing to that sum."""
class Sum:

    def __init__(self, value, blankList, isCopy=False):
        self.value = value
        self.blankList = blankList

        # If this is not a copying operation, we calculate all possible
        # assignments.
        if not(isCopy):
            self.configurations = list(_findAssignments(self.value, self.blankList))

        # Record each entry as appearing in this sum.
        for blank in blankList:
            blank._recordSum(self)

        # Flag indicating that the sum is not yet fully determined.
        self.flag = False

        """Return True if this sum is completely defined and has been processed.
        and False otherwise."""
    def isComplete(self):

        return self.flag

        """Make a copy of this sum, using the entries in entries instead of the
        original entries. (The entries in entries should be copies.)"""
    def _copy(self, entries):

        s = Sum(self.value, [entries[i.myID] for i in self.blankList], isCopy=True)
        s.configurations = self.configurations[:]

        return s

    def _filterBasedOnEntry(self, entry):
        """For a given entry, filter out all configurations that are not valid
        based on the possible values for the entry."""

        # If this sum is complete, we ignore this operation.
        if self.flag:
            return False

        # Get the index in configurations for entry.
        idx = self.blankList.index(entry)

        # Filter, allowing only possible entries for entry.
        newConfigurations = [config for config in self.configurations if config[idx] in entry.possibleValues]
        changed = (newConfigurations != self.configurations)
        self.configurations = newConfigurations

        # Report if anything changed.
        return changed

    def _banExistingConfiguration(self, config):
        """We only allow each sum to appear once in the table, e.g. if we have
        twos sum of value 10 over three entries and one of them is 1, 3, 6, then
        the other one cannot be any permutation of 1, 3, 6.

        This method accepts a configuration (a list of values) that has already
        been used, and filters all permutations of it out of the configuration
        for this row."""

        # If this sum is complete, we ignore this operation.
        if self.flag:
            return False

        newConfigurations = [c for c in self.configurations if set(c) != set(config)]
        changed = (newConfigurations != self.configurations)
        self.configurations = newConfigurations

        # Report if anything changed.
        return changed

    def _getValuesForEntry(self, entry):
        """Given an entry appearing in the sum, determine all the possible
        values it can have in the sum."""

        # Get the index in the configurations for the entry.
        idx = self.blankList.index(entry)

        # Find all possible values for entry over the configurations.
        return [config[idx] for config in self.configurations]

    def _checkCompleteAndProcess(self):
        """Check if this sum is defined, i.e. if only one configuration remains.
        If this is the case, mark it as such and set all of the entries to their
        value in the configuration."""

        if self.flag:
            return False

        changed = False
        if len(self.configurations) == 1:
            # We only do this once, and then ignore the sum as it has been fully
            # processed, so mark the flag as true to indicate this.
            self.flag = True

            # Now for every entry in this sum, set it to its value.
            for entry, value in zip(self.blankList, self.configurations[0]):
                changed |= entry._setValue(value)

        # Report if anything changed.
        return changed


class KakuroEntry:
    """The superclass for entries in a Kakuro board."""
    pass


class Blank(KakuroEntry):
    """A blank square to be filled in."""
    id = 0

    def __init__(self, value=None, specificID=None):
        self.possibleValues = [value] if value else range(1,10)

        # Give every blank an index for easy identification and to determine
        # if one blank is a copy of another.
        if specificID == None:
            self.myID = Blank.id
            Blank.id += 1
        else:
            self.myID = specificID

        # Keep track of the sums containing this blank.
        self.sums = []

    def _copy(self):
        """Create a copy of this entry.

        NOTE: We do not populate sums, as this will be done when the sums are
        duplicated."""

        # We want the same ID for comparison purposes.
        blank = Blank(specificID=self.myID)

        # Make a copy of the possible values so that we can change the possible
        # values of the copy without affecting the original.
        blank.possibleValues = self.possibleValues[:]

        return blank

    def __str__(self):
        """Return a string identifying this blank."""
        return 'E%2s' % self.myID
    
    def _recordSum(self, s):
        """Record this entry as appearing in the specified Sum object."""
        if s not in self.sums:
            self.sums.append(s)

    def _filterSumConfigurations(self):
        """Iterate over all the sums containing this blank, and filter out
        all of the configurations over those sums that contain an invalid
        entry for this blank."""

        changed = False
        for s in self.sums:
            changed |= s._filterBasedOnEntry(self)

        # Report if anything changed.
        return changed

    def _filterValuesFromSums(self):
        """This entry can only have certain values in each sum it appears in.
        The possible values that it can have overall is the intersection of
        its current possible values with the possible values in each of the
        sums in which it appears."""
        newPossibleValues = reduce(lambda x,y: [i for i in x if i in y],
                                   [set(s._getValuesForEntry(self)) for s in self.sums],
                                   self.possibleValues)
        changed = (newPossibleValues != self.possibleValues)
        self.possibleValues = newPossibleValues

        # Report if anything changed.
        return changed

    def _setValue(self, value):
        """Set the value for this entry. This triggers a reaction where we then
        iterate over the sums containing this entry and modify them
        accordingly."""

        # This value is now set in stone.
        self.flag = True
        self.possibleValues = [value]

        # Filter out all configurations from sums that don't have value for this
        # entry.
        return self._filterSumConfigurations()


class Brick(KakuroEntry):
    """A brick, i.e. solid space."""

    def __init__(self, v=None, h=None):
        self.verticalSum = v
        self.horizontalSum = h

    def __str__(self):
        return '_'


class KakuroBoard:
    """A Kakuro board is represented by a two dimensional array with entries of
    type KakuroEntry representing the data at position x,y."""

    def __init__(self, board):
        self.board = board
        self.entries = []

        # Convert to internal data structure, namely a list of sums represented
        # in the following way:
        # sum, [Blanks in the sum]
        sums = []
        for x, row in enumerate(board):
            for y, entry in enumerate(row):
                # If the entry is a sum, process the sum.
                if isinstance(entry, Brick):
                    if entry.verticalSum:
                        blocks = []
                        posx = x+1
                        col = [board[i][y] for i in range(len(board))]
                        while posx < len(col) and isinstance(col[posx], Blank):
                            blocks.append(col[posx])
                            posx += 1
                        sums.append(Sum(entry.verticalSum, blocks))
                    if entry.horizontalSum:
                        blocks = []
                        posy = y+1
                        while posy < len(row) and isinstance(row[posy], Blank):
                            blocks.append(row[posy])
                            posy += 1
                        sums.append(Sum(entry.horizontalSum, blocks))

                elif isinstance(entry, Blank):
                    self.entries.append(entry)

        # We have the structure.
        self.sums = sums

    def _copyBoard(self, entries, sums):
        """Given a board, make a copy of it. This is used in backtracking.

        NOTE: We are not making a full copy of this object so much as we are
        simply copying the entries and sums lists."""

        # The entries are specifically going to be what changes, so begin by
        # copying them.
        cEntries = [entry._copy() for entry in entries]

        # Now copy the sums. Note that doing so automatically will register each
        # of the entries with the appropriate sums as required. 
        cSums = [s._copy(cEntries) for s in sums]
        
        return cEntries, cSums

    def solve(self):
        """Solve the board."""

        # We start by making a copy, since we don't want to change the actual
        # Blank entries themselves through computation. Thus, they are preserved
        # and this class represents a fresh, clean sheet of paper with no
        # writing on it at all times.
        cEntries, cSums = self._copyBoard(self.entries, self.sums)

        # Now we use the auxiliary method on the data structure copy until all
        # the entries are solved.
        return self._solve(cEntries, cSums)
        
    def _solve(self, entries, sums):
        """Solve the board on the data structures given.

        Go as far as we can computationally, and then branch on the entry of
        fewest choices."""

        #print "\n\n*** BOARD BEFORE ***"
        #for s in sums:
        #    print 'Sum: v=%s, e=%s' % (s.value, [(e.myID,e.possibleValues) for e in s.blankList])
            
        # We process the board as far as we can.
        changed = True
        while changed:
            changed = False

            # First we check to see if any sums are now complete.
            for s in filter(lambda x:not(x.isComplete()), sums):
                # Process it.
                changed |= s._checkCompleteAndProcess()

                # Now if it is complete, we make sure that the configuration is
                # not repeated anywhere else in the puzzle.
                if s.isComplete():
                    for s2 in filter(lambda s2:s2.value == s.value and len(s2.blankList) == len(s.blankList), sums):
                        changed |= s2._banExistingConfiguration(s.configurations[0])

            # Now we iterate over entries and process them.
            for e in entries:
                # First filter the values from sums, meaning reduce the
                # possible values for this entry based on what is allowed
                # through the sums.
                changed |= e._filterValuesFromSums()

            # Iterate over the entries again now, and process further.
            for e in entries:
                # Second filter the values from configs, meaning reduce the
                # configurations over the sums so that only those accommodating
                # the possible values for this entry are considered.
                changed |= e._filterSumConfigurations()

        #print "\n\n*** BOARD AFTER ***"
        #for s in sums:
        #    print 'Sum: v=%s, e=%s' % (s.value, [(e.myID,e.possibleValues) for e in s.blankList])

        # If any entries are empty, we must backtrack.
        if reduce(operator.or_, map(lambda x:len(x.possibleValues) == 0, entries), False):
            return

        # If the board is complete, we are done.
        if reduce(operator.and_, [len(i.possibleValues) == 1 for i in entries], True):
            yield entries
        else:
            # Things are now stable, so we must branch on a possible value.
            # Pick the entry with the fewest choices to minimize branching.
            branchingEntries = sorted([(len(entry.possibleValues), entry.myID)
                                       for entry in entries if len(entry.possibleValues) > 1])
            entryID = branchingEntries[0][1]

            # Try all possible values for this entry.
            for value in entries[entryID].possibleValues:
                # Create a copy of the board.
                cEntries, cSums = self._copyBoard(entries, sums)

                # Set the value.
                cEntries[entryID]._setValue(value)

                # Iteratively solve.
                for solution in self._solve(cEntries, cSums):
                    yield solution


if __name__ == '__main__':
    #board = [[Brick(),     Brick(v=5),       Brick(v=23), Brick(v=6), Brick(v=30), Brick(),     Brick()],
    #         [Brick(h=12), Blank(),          Blank(),     Blank(),    Blank(3),    Brick(v=15), Brick(v=5)],
    #         [Brick(h=27), Blank(),          Blank(),     Blank(),    Blank(),     Blank(),     Blank()],
    #         [Brick(),     Brick(v=24,h=31), Blank(),     Blank(),    Blank(),     Blank(),     Blank()],
    #         [Brick(),     Blank(),          Brick(v=15), Brick(v=4), Blank() ,    Brick(v=3),  Brick(v=17)],
    #         [Brick(h=34), Blank(8),         Blank(),     Blank(),    Blank(),     Blank(),     Blank()],
    #         [Brick(h=32), Blank(),          Blank(),     Blank(),    Blank(),     Blank(),     Blank()]]
    #board = [[Brick(), Brick(v=18), Brick(v=8), Brick(), Brick(), Brick(v=17), Brick(v=13)],
    #         [Brick(h=21), Blank(), Blank(), Blank(), Brick(v=22,h=14), Blank(), Blank()],
    #         [Brick(h=3), Blank(), Blank(), Brick(v=23,h=21), Blank(), Blank(), Blank()],
    #         [Brick(h=27), Blank(), Blank(3), Blank(), Blank(), Brick(v=8), Brick(v=24)],
    #         [Brick(), Brick(v=16), Brick(v=4,h=24), Blank(), Blank(), Blank(), Blank(8)],
    #         [Brick(h=20), Blank(), Blank(), Blank(8), Brick(h=14), Blank(), Blank()],
    #         [Brick(h=8), Blank(), Blank(), Brick(h=17), Blank(), Blank(), Blank()]]
    board = [[Brick(), Brick(v=14), Brick(v=5), Brick(v=28), Brick(v=3), Brick(), Brick(), Brick(v=26), Brick(v=5), Brick(v=22)],
             [Brick(h=12), Blank(), Blank(), Blank(), Blank(), Brick(v=12,h=24), Blank(), Blank(), Blank(), Blank()],
             [Brick(h=23), Blank(), Blank(), Blank(), Blank(), Blank(), Brick(v=32,h=21), Blank(), Blank(), Blank()],
             [Brick(), Brick(v=7), Brick(v=39), Blank(), Brick(h=6), Blank(), Blank(), Blank(), Brick(v=24), Blank()],
             [Brick(h=20), Blank(), Blank(), Blank(), Brick(v=19,h=27), Blank(), Blank(), Blank(), Blank(), Brick(v=34)],
             [Brick(h=6), Blank(), Blank(), Brick(v=22,h=23), Blank(), Blank(), Blank(), Brick(v=13,h=15), Blank(), Blank()],
             [Brick(h=14), Blank(), Blank(), Blank(), Blank(), Brick(h=14), Blank(), Blank(), Blank(), Blank()],
             [Brick(), Brick(v=6,h=22), Blank(), Blank(), Blank(), Brick(v=4,h=16), Blank(), Blank(), Brick(v=17), Blank()],
             [Brick(h=21), Blank(), Blank(), Blank(), Brick(h=24), Blank(), Blank(), Blank(), Blank(), Blank()],
             [Brick(h=15), Blank(), Blank(), Blank(), Blank(), Blank(), Brick(h=20), Blank(), Blank(), Blank()]]

    k = KakuroBoard(board)
    #for s in k.sums:
    #    print '%2s' % s.value, [i.__str__() for i in s.blankList]

    for entries in k.solve():
        print '*** SOLUTION ***'
        #for entry in entries:
        #    print '%2s: %s' % (entry.myID, entry.possibleValues)
        str = ''
        for row in board:
            for col in row:
                if isinstance(col, Brick):
                    str += '_ '
                else:
                    str += '%s ' % entries[col.myID].possibleValues[0]
            str += '\n'
        print str
