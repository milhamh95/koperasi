# koperasi

koperasi is a loan, save, and withdraw money system. The features are:

- Fetch all koperasi members
- Add koperasi member
- Able to loan, pay installment, save, and withdraw money
- Fetch transaction histories
- Get a member transaction detail

## Prerequisites
- Java 17
- Docker

## Postman Collection

Please import `koperasi.postman_collection.json` to your postman

## How To Start

- Run postgres database with docker compose with command
```sh
make postgres-up
```

- Run koperasi Java app with your IDE