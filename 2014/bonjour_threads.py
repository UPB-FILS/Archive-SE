"""
Le premier prgramme en Python
* utilisation des arguments de la lignne de commande
* les listes et la fonction map
* les threads
* le logger

@author Dragos STOICA
@version 0.3
@date 16.feb.2014
"""

import sys, threading, logging

class Bonjour(threading.Thread):
    def __init__(self, personne):
        threading.Thread.__init__(self)
        self.personne = personne
    def run(self):
        #Fonction polie - saluer une personne
        print "Bonjour %(personne)s !\n" % \
          {"personne":self.personne}
        logging.info("From %s(thread_name)\n" %{"thread_name":self.getName()})
   
def utilisation():
    #Affichage mode d'utilisation
    print """
          Le programme doit etre appelle avec minimum 1 argument:
          python bonjour_listes.py Dragos
          """

def main(argv=None):
    #La boucle principale
    if argv is None:
        argv = sys.argv

    if len(argv) == 1:
        utilisation()
    else:
        #Dites bonjour a chaque personne de la liste
        for nom in sys.argv[1:]:
            monThread = Bonjour(nom)
            monThread.start()
    print "Programme principal execution terminee.\n"                
    return 0

if __name__ == "__main__":
    #Simplifiez la logique de la fonction principale
    sys.exit(main())