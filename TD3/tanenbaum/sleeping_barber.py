from threading import Thread, Lock, Event
import time, random

#
# ref: https://github.com/bragisig/python-sleeping-barber/blob/master/sleeping_barber.py
# 

mutex = Lock()

#Interval in seconds
customerIntervalMin = 5
customerIntervalMax = 15
haircutDurationMin = 3
haircutDurationMax = 15

class BarberShop:
	waitingCustomers = []

	def __init__(self, barber, numberOfSeats):
		self.barber = barber
		self.numberOfSeats = numberOfSeats
		print 'BarberShop initilized with {0} seats'.format(numberOfSeats)
		print 'Customer min interval {0}'.format(customerIntervalMin)
		print 'Customer max interval {0}'.format(customerIntervalMax)
		print 'Haircut min duration {0}'.format(haircutDurationMin)
		print 'Haircut max duration {0}'.format(customerIntervalMax)
		print '---------------------------------------'

	def openShop(self):
		print 'Barber shop is opening'
		workingThread = Thread(target = self.barberGoToWork)
		workingThread.start()

	def barberGoToWork(self):
		while True:
			mutex.acquire()

			if len(self.waitingCustomers) > 0:
				c = self.waitingCustomers[0]
				del self.waitingCustomers[0]
				mutex.release()
				self.barber.cutHair(c)
			else:
				mutex.release()
				print 'Aaah, all done, going to sleep'
				barber.sleep()
				print 'Barber woke up'
	
	def enterBarberShop(self, customer):
		mutex.acquire()
		print '>> {0} entered the shop and is looking for a seat'.format(customer.name)

		if len(self.waitingCustomers) == self.numberOfSeats:
			print 'Waiting room is full, {0} is leaving.'.format(customer.name)
			mutex.release()
		else:
			print '{0} sat down in the waiting room'.format(customer.name)	
			self.waitingCustomers.append(c)	
			mutex.release()
			barber.wakeUp()

class Customer:
	def __init__(self, name):
		self.name = name

class Barber:
	barberWorkingEvent = Event()

	def sleep(self):
		self.barberWorkingEvent.wait()

	def wakeUp(self):
		self.barberWorkingEvent.set()

	def cutHair(self, customer):
		#Set barber as busy 
		self.barberWorkingEvent.clear()

		print '{0} is having a haircut'.format(customer.name)

		randomHairCuttingTime = random.randrange(haircutDurationMin, haircutDurationMax+1)
		time.sleep(randomHairCuttingTime)
		print '{0} is done'.format(customer.name)


if __name__ == '__main__':
	customers = []
	customers.append(Customer('Bragi'))
	customers.append(Customer('Auja'))
	customers.append(Customer('Iris'))
	customers.append(Customer('Axel'))
	customers.append(Customer('Andrea'))
	customers.append(Customer('Agnar'))
	customers.append(Customer('Mamma'))
	customers.append(Customer('Solla'))
	customers.append(Customer('Olla'))
	customers.append(Customer('Berglind'))
	customers.append(Customer('Bergdis'))
	customers.append(Customer('Margret'))
	customers.append(Customer('Brynjar'))
	customers.append(Customer('Siggi'))
	customers.append(Customer('Tomas'))
	customers.append(Customer('Kristrun'))
	customers.append(Customer('Heidrun'))

	barber = Barber()

	barberShop = BarberShop(barber, numberOfSeats=3)
	barberShop.openShop()

	while len(customers) > 0:
		c = customers.pop()	
		#New customer enters the barbershop
		barberShop.enterBarberShop(c)
		customerInterval = random.randrange(customerIntervalMin,customerIntervalMax+1)
		time.sleep(customerInterval)

		

