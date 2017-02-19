"""
Scheduler en Python

Ce mechanisme peut-etre accompagnie par Queue

@author Dragos STOICA
@version 0.1
@date 19.feb.2017
"""

import sched
import time

scheduler = sched.scheduler(time.time, time.sleep)

def print_event(name):
    print 'EVENT:', time.time(), name

now = time.time()
print 'START:', now
scheduler.enterabs(now+2, 2, print_event, ('first',))
scheduler.enterabs(now+2, 1, print_event, ('second',))

scheduler.run()