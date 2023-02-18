
Run spring boot app.
open H2 console: http://localhost:8080/h2-console

 host : jdbc:h2:mem:mydb
 username : sa
 password : password

Run following Insert script


Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (1,1,120.00,'2023-03-01');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (2,1,130.00,'2023-01-01');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (3,1,150.00,'2023-01-20');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (4,1,80.00,'2023-02-01');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (5,1,50.00,'2023-02-06');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (6,1,520.00,'2023-02-15');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (7,1,1000.00,'2023-01-30');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (8,1,10.00,'2023-03-01');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (9,1,80.00,'2023-02-01');
Insert into CUSTOMER_TRANSACTION (TRANSACION_ID,CUSTOMER_ID,TRANS_AMOUNT,TRANS_DATE) values (10,1,120.00,'2023-02-01');


request 1: 

curl --location --request GET 'localhost:8080/rewards'

response 1:

[
    {
        "customerId": 3,
        "totalRewards": 620,
        "transactions": {
            "2023-02": {
                "totalRewardsPerMonth": 620,
                "transations": [
                    {
                        "transactionId": 1,
                        "transactionAmt": 180.00,
                        "rewardPoints": 210
                    },
                    {
                        "transactionId": 2,
                        "transactionAmt": 280.00,
                        "rewardPoints": 410
                    },
                    {
                        "transactionId": 3,
                        "transactionAmt": 30.00,
                        "rewardPoints": 0
                    }
                ]
            }
        }
    }
]

request 2:

curl --location --request GET 'localhost:8080/rewardsByCustomerId/3'

response 2:

{
    "customerId": 3,
    "totalRewards": 620,
    "transactions": {
        "2023-02": {
            "totalRewardsPerMonth": 620,
            "transations": [
                {
                    "transactionId": 1,
                    "transactionAmt": 180.00,
                    "rewardPoints": 210
                },
                {
                    "transactionId": 2,
                    "transactionAmt": 280.00,
                    "rewardPoints": 410
                },
                {
                    "transactionId": 3,
                    "transactionAmt": 30.00,
                    "rewardPoints": 0
                }
            ]
        }
    }
}


Save Transaction:

curl --location --request POST 'localhost:8080/saveTransaction' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "customerId": 3,
        "transactionAmt": 180.00
    },
    {
        "customerId": 3,
        "transactionAmt": 280.00
    },
    {
        "customerId": 3,
        "transactionAmt": 30.00
    }
]'
