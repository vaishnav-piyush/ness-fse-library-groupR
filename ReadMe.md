********************************************************************************
A) Windows Docker Setup
********************************************************************************
	1)	Install the Docker tool box
	2)	Run mvn clean package from outside
	3)	Run java –jar <jarname>
	4)	http://localhost:8080   check working
	5)	Package in docker
	Run the C:\Users\NRajagopalan\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Docker quickstart
	https://docs.docker.com/docker-for-windows/  if you need from cmd else do it from docker quickstart
	6)	Spotify method 
		a.	docker ps
		b.	Run mvn install dockerfile:build
		c.	docker run -p 8080:8080 -t springio/gs-spring-boot-docker
		d.	http://192.168.99.100:8080/ 
********************************************************************************
B) Ubuntu Docker Setup
********************************************************************************
Host Setup:
	a) Download the image for ubuntu: from https://www.ubuntu.com/download/
	b) Setup a ubuntu VM with virtual box ie hypervisor
		* host only adaptor
		* storage - empty - select downloaded iso
		* start VM - chose english
		* default option first few screens
		* all default
		* enter hostname properly 
		* naveen/naveen15
	c) Install JDK on the ubuntu: 
		sudo apt-get update
		sudo apt-get install default-jre
		sudo apt-get install java-package.
		sudo apt-get install default-jdk

Docker Setup on host:
		* apt-get update
		* apt-get install -y docker.io
		* service docker.io status
		* docker version
		* docker info to know number of containers and images
		* OPTIONAL: incase you need to update docker : wget -q0 https://get.docker.com/gpg | apt-key add -naveen
		naveen15
		* OPTIONAL add docker repo : echo deb http://get.docker.com/ubuntu docker main > etc/apt/sources.list.d/docker.list
		* apt-get update
		* OPTIONAL apt-get install install lxc-docker
		* OPTIONAL incase space issue https://askubuntu.com/questions/944588/apt-get-f-install-no-space-left-on-device-ubuntu-14-04
********************************************************************************		
Docker command sets:
	List Images: docker images
	Remove all container, create blank slate: docker system prune 
	remove inactive containers: docker rm $(docker ps -aq)
	remove images: docker rmi $(docker images -q)
	Get the IPAddress of docker machine : docker inspect spring-demo-mongo | grep IPAddress
	List of stopped containers : docker ps -a gets 
	List the env : docker-machine ls
	# As of Docker v1.3 you can attach to a bash shell
	docker exec -it  2e23d01384ac  bash
	Start Containers: 
			docker ps -a
	 		docker start -ai 11cc47339ee1er 
	docker-compose up
	docker network ls
	docker-compose scale discovery-service=2
********************************************************************************	
	Multi container setup for microservices with angularJS and mongo
********************************************************************************
1) Docker Machine pre-requisites:
****************************************************************************************	
	a) Setting up network for containers to talk to each other:
			docker network create spring_demo_net 

	b) Setup volume:
			mkdir -p ~/mongo-data  
****************************************************************************************	
2) Create/Start Mongo Container:
****************************************************************************************	

A) CREATE MONGO CONTAINER
	* docker run --name spring-demo-mongo -p 27017:27017 --network=spring_demo_net -v /home/ubuntu/mongo-data:/data/db -d mongo 
	* check if running : docker ps
	OR
B) START MONGO CONTAINER
	* docker ps -a
	* docker start -ai 11cc47339ee1er 
	* connect the client with the IP/Host IP
	* with hypervisor ensure port forwarding is done for mongo port to access from outside if ubuntu
****************************************************************************************		
3) Create/Start Microservices
****************************************************************************************	
	* start registry on 8765
	* start library on random port
	* start user on random port
	* start gateway on 8761
	* start the UI
A) Build Images : 
	Assuming the jars are ready for each: mvn clean package on each project if not
	cd /e/Naveen_Home/FSE_HOME/Docker/FSE_FINAL_CODE/registry
	docker build --tag=registry-1.0 .
	cd /e/Naveen_Home/FSE_HOME/Docker/FSE_FINAL_CODE/gateway
	docker build --tag=gateway-1.0 .
	cd /e/Naveen_Home/FSE_HOME/Docker/FSE_FINAL_CODE/library
	docker build --tag=library-1.0 .
	cd /e/Naveen_Home/FSE_HOME/Docker/FSE_FINAL_CODE/user
	docker build --tag=user-1.0 .
	docker images

B) Run Images :
	docker run -d --name registry --network=spring_demo_net -p 8761:8761  registry-1.0
	docker run -d --name gateway --network=spring_demo_net --add-host="localhost:192.168.99.100" -p 8765:8765  gateway-1.0 .
	docker run -d --name library --network=spring_demo_net library-1.0 .  
	docker run -d --name user --network=spring_demo_net user-1.0 .  

C) Build and Run UI Image

	cd /e/Naveen_Home/FSE_HOME/Docker/FSE_FINAL_CODE/library-admin-ui
	docker build -t naveen/library-admin-ui .
	docker run --network=spring_demo_net -p 4200:4200 -d naveen/library-admin-ui
****************************************************************************************		
4) Testing the services are up:
****************************************************************************************		
	1) Check if discovery is started: 	http://192.168.99.100:8761/ 
	2) check if library is registered in eureka after starting on random port
	3) check if the gateay is registerd in discovery : http://192.168.99.100:8761/
	4) docker logs <> to check the running logs
	5) http://192.168.99.100:8765/fse/book
	6) http://dev-fse.service.com:4200/available-books
****************************************************************************************	

       
	




