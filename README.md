# rest

curl -v -X POST -H 'Content-Type:application/json' 'http://localhost:8081/make-payment?accountNumber=123456A&toAccountNumber=123456B&amount=100&customerID=1'

curl http://localhost:8081/account-balance?accountNumber=123456A

curl http://localhost:8081/customer?username=jon01

curl http://localhost:8081/customer-transactions?username=jon01

curl http://localhost:8081/all-accounts-for-customer?accountID=1


