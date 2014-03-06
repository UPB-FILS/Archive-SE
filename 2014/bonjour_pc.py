"""
Le premier prgramme en Python
* utilisation des arguments de la lignne de commande
* les listes et la fonction map
* les threads
* le logger
* Producer Consumer

@author Dragos STOICA
@version 0.5
@date 16.feb.2014
"""

import sys, threading, logging, os, Queue

class Producer(threading.Thread):
    """
    Producer class that takes the raw data and performs some work on it.
    Once this work is complete it puts the resultant data in to the out queue
    for the Consumer class.
    """
    def __init__(self, in_queue, fichier, lock):
        threading.Thread.__init__(self)
        self.in_queue = in_queue
        self.fichier = fichier
        self.lock = lock
    def run(self):
        self.lock.acquire()
        with open(self.fichier,'r') as f:
            #Dites bonjour a chaque personne de fichier
            for ligne in f:       
                self.in_queue.put(ligne.strip(' \r\n'))
                logging.info("Producer: %s"%(ligne.strip(' \r\n')))
        #signal that the worker task is done
        self.lock.release()
        logging.info("Producer stop.")

class Consumer(threading.Thread):
    """
    Consumer class that takes the data outputted by the Producer class and
    consumes the data from the out queue and outputs a meaningful result.
    """
    def __init__(self, in_queue, lock):
        threading.Thread.__init__(self)
        self.in_queue = in_queue
        self.M = []
        self.Mme = []
        self.lock = lock
    def run(self):
        self.lock.acquire()
        while True:
            #grab the data from the out queue
            item = self.in_queue.get()
            if item[0:2] == "M.":
                self.M.append(item)
            else:
                self.Mme.append(item)
            logging.info("Consumer : %(personne)s" %{"personne":item})
           
            #signal that the consumer task is done
            self.in_queue.task_done()
        self.lock.release()
            
    def write_file(self, fichier):
        f = open(fichier + "pc_resultat.txt",'w')
        for mme in self.Mme:
            #Fonction polie - saluer une personne
            f.write("Bonjour {}!\n".format(mme))
        for m in self.M:
            #Fonction polie - saluer une personne
            f.write("Bonjour {}!\n".format(m))  
        f.close()
   
def utilisation():
    #Affichage mode d'utilisation
    print """
          Le programme doit etre appelle avec minimum 1 argument:
          python bonjour_listes.py Nom_de_fichier.txt
          """

def main(argv=None):
    working_dir = os.path.dirname(os.path.abspath(__file__)) + "\\"
    #Configurez le logging pour ecrire dans un fichier texte
    logging.basicConfig(format='%(asctime)s %(levelname)s %(message)s',
                        filename = working_dir + 'pc.log',
                        level=logging.INFO)    
    logging.info("Main start")
    
    #La boucle principale
    if argv is None:
        argv = sys.argv

    if len(argv) == 1:
        utilisation()
    else:
        #Argument 1 est le nom de fichier avec un noms per ligne
        in_queue = Queue.Queue()
        lock = threading.Lock()
        p = Producer(in_queue, working_dir + argv[1],lock)
        p.start()
        
        #spawn our consumer threads and start consuming
        c = Consumer(in_queue,lock)
        c.daemon = True
        c.start() #invoke method run

        #attendez que les donnees soit traitees
        in_queue.join()
        c.write_file(working_dir)
        
    logging.info("Main stop")                
    return 0

if __name__ == "__main__":
    #Simplifiez la logique de la fonction principale
    sys.exit(main())
