Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.synced_folder ".", "/vagrant", type: "virtualbox"
  
  
  # Zookeeper port (binary)
  config.vm.network "forwarded_port", guest: 2181, host: 2181 # Zookeeper
  # Kafka-connect port (REST)
  config.vm.network "forwarded_port", guest: 9091, host: 9091
  config.vm.network "forwarded_port", guest: 9092, host: 9092
  config.vm.network :forwarded_port, guest: 27017, host: 27017 #MongoDB
  config.vm.network "forwarded_port", guest: 3000, host: 3000
  config.vm.network "forwarded_port", guest: 3306, host: 3306 # Mysql
  
  
	config.vm.provider "virtualbox" do |v|
	  v.memory = 2048
	  v.cpus = 4
	end
end
