import socket               

sc = socket.socket()         
host = socket.gethostname() 
port = 12345                
sc.bind((host, port))        

sc.listen(5)                 
while True:
   c, addr = sc.accept()     
   print 'Connecting with ', addr
   c.send('Connecting successful')

   import RPi.GPIO as GPIO	#for LED
   import time

   GPIO.setwarnings(False)
   GPIO.setmode(GPIO.BCM)
   GPIO.setup(18,GPIO.OUT)

   state = True

   while True:
    GPIO.output(18,True)
    time.sleep(1)
    GPIO.output(18,False)
    time.sleep(1)
   c.close()                


