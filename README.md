# nebula
A multi-node cluster based distributed storage system (in progress)
- Also contains a Spring Boot application to fetch and show the latest trending hashtags in tweets of a selected location (using nebula as the persistence layer). 

## Prerequisites
- Java (1.8)
- Maven
- IntelliJ IDEA
- curl (optional for testing locally)



## How to run
- Clone project by running 
		
        git clone git@github.com:haverma/nebula.git

- Install project dependencies and build project by running the following command from the root project directory
		
        mvn -f projects/pom.xml install
        
#### Setting up and running in IntelliJ
- Open project `nebula/projects`
- Select `Enable Auto Import` from IntelliJ pop-up
- Run `run-nebula` from the Run menu
- Store a key-value pair by running

		curl --header "Content-Type: application/json" --request PUT --data '{"key":"hah","value":"blah"}' http://localhost:5001/nebula/put
        
- Get value for a given key by running in a web-browser

		http://127.0.0.1:5001/nebula/get/hah
        
- Delete a key-value pair by running
		
        curl --header "Content-Type: application/json" --request DELETE http://localhost:5001/nebula/delete/hah
