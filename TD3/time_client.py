"""
Le premier client en Python
* Client

Ce client se connecte au Time serveur et recupere l'heure de serveur.

@author Dragos STOICA
@version 0.1
@date 19.feb.2017
"""

import socket

# create a socket object
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 

# get local machine name
host = socket.gethostname()

port = 6666

# connection to hostname on the port.
s.connect((host, port))

# Receive no more than 1024 bytes
tm = s.recv(1024)

s.close()

print("The time got from the server is %s" % tm.decode('ascii'))
