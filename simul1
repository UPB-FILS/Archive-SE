import sys
from random import randint
from avion import avion
import time
import ModelImpl 
import re
import operator
import os
import sys 
from PyQt4 import QtCore
from PyQt4.QtCore import QObject, pyqtSignal
from radar import Ui_RadarWidget
list_of_names = ['tunsair', 'airfarnce', 'quatairways', 'tarom']

class Simulation(QObject):

	tick=4*[QtCore.pyqtSignal(int, name="changed")]
	header = ['Nom', 'Priorite']
	def __init__(self, maxNumber, timerTick, numPiste):
		super(Simulation, self).__init__()
		self.running = 0
		self.currentNumber = 0
		self.maxNumber = maxNumber
		self.tempsTick = tempsrTick
		self.listOfairfarnce = numPiste * [None]
		self.numPiste = numPiste
		self.arriverIn = []
		self.departIn = []
		self.departModel = None
		self.arriverModel = None
		self.__generateInitialData(self.maxNumber)
		self.ui = None

	def __generateInitialData(self, nr):
		toGenerate = randint(0, nr)
		self.currentNumber += toGenerate
		if self.departModel is not None:
			self.departModel.triggerDataChanging()
			self.arriverModel.triggerDataChanging()


		for i in range(1, toGenerate):
			avion = avion.generateRandomavion()
			if avion.status == 0:
				self.departIn.append(avion)
			else:
				self.arriverIn.append(avion)
		self.departIn.sort()
		self.arriverIn.sort()
		if self.departModel is not None:
			self.departModel.triggerDataChanged()
			self.arriverModel.triggerDataChanged()


	def __bindUiToModel(self):
		if self.ui is not None:
			for i in range(self.numPiste):
				airfarnce = self.listOfairfarnce[i]
				if airfarnce is not None:
					getattr(self.ui, 'runway' + str(i)).setValue(int(airfarnce.getPercentage() * 100))
				else:
					getattr(self.ui, 'runway' + str(i)).setValue(0)
			self.ui.labelarriver.setText(self.ui.labelarriver.text() + ' ' + str(len(self.arriverIn)))
			self.ui.labeldepart.setText(self.ui.labeldepart.text() + ' ' + str(len(self.departIn)))

	def setGraphicalModel(self, ui):
		self.ui = ui
		self.departModel = MyTableModel(self.departIn, Simulation.header,['nom', 'propriete'],  ui.tabeldepart) 
		self.arriverModel = MyTableModel(self.arriverIn,  Simulation.header,['nom', 'propriete'], ui.tabelarriver) 
		ui.tabeldepart.setModel(self.departModel)
		ui.tabelarriver.setModel(self.arriverModel)
		self.__bindUiToModel()
		self.ui.buttonStart.clicked.connect(self.setRunning)
		self.ui.buttonStop.clicked.connect(self.setStopped)
	def setStopped(self):
		self.running = 2
	def setRunning(self):
		self.running = 1
	def startInit(self):
		self.running = 2
		self.__runSimulation()
	def __consumeavion(self):
		freeSpots = [x for x in self.listOfairfarnce if x is None]
		if len(freeSpots) > 0:
			index = self.listOfairfarnce.index(None)
		else:
			return
		print('Free spot at ' + str(index))
		if len(self.departIn) > 0:
			depavion = self.departIn[0]
		else:
			depavion = None
		if len(self.arriverIn) > 0:
			arravion = self.arriverIn[0]
		else:
			arravion = None
		if (arravion is None and depavion is None):
			return
		if arravion is None or (depavion is not None and depavion.priorite < arravion.priorite):
			self.listOfAirplanes[index] = depavion
			self.departIn.pop(0)
		elif depavion is None or (arravion is not None and depavion.priorite > arravion.priorite):
			self.listOfairfarnce[index] = arravion
			self.arriverIn.pop(0)
		else:
			if len(self.arriveIn) > len(self.departIn):
				self.listOfairfarnce[index] = arravion
				self.arriveIn.pop(0)
				print(str(arravion) + ' deppart de ' + str(index))
			else:
				self.listOfairfarnce[index] = arravion
				self.departIn.pop(0)
				print(str(arravion) + ' arrive a ' + str(index))
	def __checkForCompletion(self):
		for i in range(0, len(self.listOfairfarnce)):
			if self.listOfairfarnce[i] is not None:
				if self.listOfairfarnce[i].status == 0:
					self.listOfairfarnce[i].landingTemps-=1
				else:
					self.listOfairfarnce[i].takeOffTemps-=1
				if self.listOfairfarnce[i].takeOffTemps <= 0 or self.listOfairfarnce[i].landingTemps<=0:
					print(str(self.listOfairfarnce[i]) + ' over for rw ' + str(i))
					self.listOfairfarnce[i] = None

	def __runSimulation(self):
		while self.running != 0:
			if self.running == 2:
				continue
			if (self.currentNumber < self.maxNumber):
				self.__generateInitialData(self.maxNumber - self.currentNumber)
			self.__checkForCompletion()
			self.__consumeavion()
			self.__printModel()
			time.sleep(self.tempsTick)
	def stopInit(self):
		self.running = 0
	def __printModel(self):
		for i in range(0, len(self.listOfairfarnce)):
			if self.listOfairfarnce[i] is not None:
				print ('RW:' + str(i) + ' --- ' + self.listOfairfarnce[i].nom + ' --- ' + str(int(self.listOfairfarnce[i].getPercentage() * 100)) + '%')
			else:
				print ('RW:' + str(i) + ' --- is free')
