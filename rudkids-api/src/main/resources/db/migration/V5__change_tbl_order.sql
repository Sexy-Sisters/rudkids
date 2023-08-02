alter table tbl_order drop column delivery_status;

alter table tbl_order add column refund_account_name varchar(255) null;
alter table tbl_order add column refund_bank_code varchar(255) null;
alter table tbl_order add column refund_holder_name varchar(255) null;