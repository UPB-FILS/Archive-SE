"""
Thread Pool en Python

A partir de Python v3.2 ThreadPool est native via ThreadPoolExecutor
https://docs.python.org/dev/library/concurrent.futures.html#threadpoolexecutor

@author Dragos STOICA
@version 0.1
@date 19.feb.2017
"""

from multiprocessing.dummy import Pool as ThreadPool

def squareNumber(n):
    return n ** 2

# function to be mapped over
def calculateParallel(numbers, threads=2):
    pool = ThreadPool(threads)
    results = pool.map(squareNumber, numbers)
    pool.close()
    pool.join()
    return results

if __name__ == "__main__":
    numbers = [1, 2, 3, 4, 5]
    squaredNumbers = calculateParallel(numbers, 4)
    for n in squaredNumbers:
        print(n)