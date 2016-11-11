#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <wiringPi.h>
#define LED     0

int main(){
  int WSocket, nSocket;
  char buffer[1024];
  struct sockaddrIN serverAddr;
  struct sockaddrStore serverStorage;
  socklen_t addr_size;

  WSocket = socket(PF_INET, SOCK_STREAM, 0);
  serverAddr.sin_family = AF_INET;
  serverAddr.sin_port = htons(7891);
  serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1"); 
  memset(serverAddr.sin_zero, '\0', sizeof serverAddr.sin_zero);

  bind(WSocket, (struct sockaddr *) &serverAddr, sizeof(serverAddr));

  if(listen(WSocket,5)==0)
    printf("Listening\n");
  else
    printf("Error\n");
  
  addr_size = sizeof serverStorage;
  nSocket = accept(WSocket, (struct sockaddr *) &serverStorage, &addr_size);
        wiringPiSetup () ;
        pinMode (LED, OUTPUT) ;

        for (;;)
        {
        digitalWrite (LED, HIGH) ;     
        delay (500) ;          
        digitalWrite (LED, LOW) ;       
        delay (500) ;
        }
  
  strcpy(buffer,"Hi! \n");
  send(nSocket,buffer,13,0);

  return 0;
}
