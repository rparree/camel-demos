#! /bin/sh
curl -X POST -H "Content-Type: application/json" -w "\n"  -d '{registration:{username:"jenny",email:"jenny@work.com"}}' http://localhost:9090/demo/register

curl -X POST -H "Content-Type: application/xml" -H "Accept: application/xml" -w "\n"  -d '<registration><username>jenny</username><email>jenny@work.com</email></registration>' http://localhost:9090/demo/register
