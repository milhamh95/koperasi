CREATE SEQUENCE IF NOT EXISTS member_seq;

CREATE TABLE "public"."member" (
    "id" int4 NOT NULL DEFAULT nextval('member_seq'::regclass),
    "name" varchar(255),
    "address" varchar(255),
    "created_time" timestamp,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE IF NOT EXISTS transaction_seq;
DROP TYPE IF EXISTS "public"."transaction_type";
CREATE TYPE "public"."transaction_type" AS ENUM ('LOAN', 'INSTALLMENT', 'SAVE', 'WITHDRAW');

-- Table Definition
CREATE TABLE "public"."transaction" (
    "id" int4 NOT NULL DEFAULT nextval('transaction_seq'::regclass),
    "member_id" int4,
    "type" "public"."transaction_type",
    "total" int4,
    "created_time" timestamp,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE IF NOT EXISTS loan_seq;
DROP TYPE IF EXISTS "public"."loan_status";
CREATE TYPE "public"."loan_status" AS ENUM ('PAID', 'NOT_PAID');

CREATE TABLE "public"."loan" (
    "id" int4 NOT NULL DEFAULT nextval('loan_seq'::regclass),
    "transaction_id" int4,
    "member_id" int4,
    "total" int4,
    "loan_date" date,
    "tenor" date,
    "status" "public"."loan_status",
    "created_time" timestamp,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE IF NOT EXISTS installment_seq;
CREATE TABLE "public"."installment" (
    "id" int4 NOT NULL DEFAULT nextval('installment_seq'::regclass),
    "loan_id" int4,
    "total" int4,
    "loan_remainder" int4,
    "created_time" timestamp,
    "transaction_id" int4,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE IF NOT EXISTS saving_seq;

CREATE TABLE "public"."saving" (
    "id" int4 NOT NULL DEFAULT nextval('saving_seq'::regclass),
    "member_id" int4,
    "transaction_id" int4,
    "type" "public"."transaction_type",
    "total" int4,
    "current_saving" int4,
    "created_time" timestamp,
    PRIMARY KEY ("id")
);


INSERT INTO "public"."member" ("id", "name", "address", "created_time") VALUES
(1, 'John', 'Jakarta', '2022-06-21 15:44:46.400525');

INSERT INTO "public"."transaction" ("id", "member_id", "type", "total", "created_time") VALUES
(1, 1, 'LOAN', 1000000, '2022-06-21 15:44:58.679628'),
(2, 1, 'INSTALLMENT', 200000, '2022-06-21 15:45:10.697624'),
(3, 1, 'SAVE', 100000000, '2022-06-21 15:45:13.849079'),
(4, 1, 'WITHDRAW', 200000, '2022-06-21 15:45:16.591341'),
(5, 1, 'WITHDRAW', 200000, '2022-06-21 15:45:24.989348');

INSERT INTO "public"."installment" ("id", "loan_id", "total", "loan_remainder", "created_time", "transaction_id") VALUES
(1, 1, 200000, 800000, '2022-06-21 15:45:10.697624', 2);

INSERT INTO "public"."loan" ("id", "transaction_id", "member_id", "total", "loan_date", "tenor", "status", "created_time") VALUES
(1, 1, 1, 1000000, '2022-06-21', '2022-06-25', 'NOT_PAID', '2022-06-21 15:44:58.679628');

INSERT INTO "public"."saving" ("id", "member_id", "transaction_id", "type", "total", "current_saving", "created_time") VALUES
(1, 1, 3, 'SAVE', 100000000, 100000000, '2022-06-21 15:45:13.849079'),
(2, 1, 5, 'WITHDRAW', 200000, 99800000, '2022-06-21 15:45:24.989348');

ALTER TABLE "public"."installment" ADD FOREIGN KEY ("loan_id") REFERENCES "public"."loan"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."installment" ADD FOREIGN KEY ("transaction_id") REFERENCES "public"."transaction"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."loan" ADD FOREIGN KEY ("member_id") REFERENCES "public"."member"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."loan" ADD FOREIGN KEY ("transaction_id") REFERENCES "public"."transaction"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."saving" ADD FOREIGN KEY ("transaction_id") REFERENCES "public"."transaction"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."saving" ADD FOREIGN KEY ("member_id") REFERENCES "public"."member"("id") ON DELETE SET NULL ON UPDATE SET NULL;
ALTER TABLE "public"."transaction" ADD FOREIGN KEY ("member_id") REFERENCES "public"."member"("id") ON DELETE SET NULL ON UPDATE SET NULL;