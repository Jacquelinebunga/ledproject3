import socket               

sc = socket.socket()         
host = socket.gethostname() 
port = 12345                

sc.connect((host, port))

print sc.recv(1024)

sc.close                     

