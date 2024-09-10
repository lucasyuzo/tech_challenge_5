build-product:
	cd product; mvn compile; mvn package; docker build -t product_app:latest -f ./Dockerfile .

build-cart:
	cd cart; mvn compile; mvn package; docker build -t cart_app:latest -f ./Dockerfile .

build-payment:
	cd payment; mvn compile; mvn package; docker build -t payment_app:latest -f ./Dockerfile .

build-user:
	cd user; mvn compile; mvn package; docker build -t user_app:latest -f ./Dockerfile .

build: build-product build-cart build-payment build-user
	@echo "---| BUILT ALL APPLICATIONS |---"

run: build
	docker compose up
