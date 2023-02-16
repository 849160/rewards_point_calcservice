# rewards_point_calcservice


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

curl --location --request GET 'localhost:8080/rewards' \
--header 'Content-Type: application/json' \
--data-raw '{"transactionAmount" : 120}'

response 1:

90

request 2:

curl --location --request GET 'localhost:8080/rewardsByCustId' \
--header 'Content-Type: application/json' \
--data-raw '{"custId": 1}'

response 2:

{
    "custId": 1,
    "totalRewardsPoint": 3240,
    "transactions": {
        "2023-02": {
            "monthyRewardPoint": 1040,
            "transations": [
                {
                    "transId": 4,
                    "transAmt": 80.00,
                    "rewardPoint": 30
                },
                {
                    "transId": 5,
                    "transAmt": 50.00,
                    "rewardPoint": 0
                },
                {
                    "transId": 6,
                    "transAmt": 520.00,
                    "rewardPoint": 890
                },
                {
                    "transId": 9,
                    "transAmt": 80.00,
                    "rewardPoint": 30
                },
                {
                    "transId": 10,
                    "transAmt": 120.00,
                    "rewardPoint": 90
                }
            ]
        },
        "2023-01": {
            "monthyRewardPoint": 2110,
            "transations": [
                {
                    "transId": 2,
                    "transAmt": 130.00,
                    "rewardPoint": 110
                },
                {
                    "transId": 3,
                    "transAmt": 150.00,
                    "rewardPoint": 150
                },
                {
                    "transId": 7,
                    "transAmt": 1000.00,
                    "rewardPoint": 1850
                }
            ]
        },
        "2023-03": {
            "monthyRewardPoint": 90,
            "transations": [
                {
                    "transId": 1,
                    "transAmt": 120.00,
                    "rewardPoint": 90
                },
                {
                    "transId": 8,
                    "transAmt": 10.00,
                    "rewardPoint": 0
                }
            ]
        }
    }
}

