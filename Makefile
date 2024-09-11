build-product:
	cd product; mvn compile; mvn clean package; docker build --no-cache -t product_app:latest -f ./Dockerfile .

build-cart:
	cd cart; mvn compile; mvn clean package; docker build --no-cache -t cart_app:latest -f ./Dockerfile .

build-payment:
	cd payment; mvn compile; mvn clean package; docker build --no-cache -t payment_app:latest -f ./Dockerfile .

build-user:
	cd user; mvn compile; mvn clean package; docker build --no-cache -t user_app:latest -f ./Dockerfile .

build: build-product build-cart build-payment build-user
	@echo "---| BUILT ALL APPLICATIONS |---"

run: build
	docker compose up
