"""
Le premier prgramme en Python

@author Dragos STOICA
@version 0.1
@date 16.feb.2014
"""

def dites_bonjour(personne):
    print "Bonjour " + personne + " !"

if __name__ == "__main__":
    import sys
    dites_bonjour(sys.argv[1])