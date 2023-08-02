alter table tbl_item
    add column mystery_product_id binary(16) null,
add constraint FKtju2cxa4jdl3g2jbp61wuhy4m
        foreign key (mystery_product_id) references tbl_mystery_product (mystery_product_id);