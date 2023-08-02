drop table tbl_product_banner_image;

alter table tbl_product add column banner_path varchar(255) null;
alter table tbl_product add column banner_url varchar(255) null;

alter table tbl_product change column mobile_path mobile_banner_path varchar(255) null;
alter table tbl_product change column mobile_url mobile_banner_url varchar(255) null;

alter table tbl_product drop column mystery;
alter table tbl_product drop column product_status;

alter table tbl_item drop column mystery;

alter table tbl_item add column mystery_product_id binary(16) null;

add constraint FKtju2cxa4jdl3g2jbp61wuhy4m
        foreign key (mystery_product_id) references tbl_mystery_product (mystery_product_id);
