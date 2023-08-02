create table tbl_mystery_product
(
    mystery_product_id binary(16)   not null
        primary key,
    back_path          varchar(255) null,
    back_url           varchar(255) null,
    banner_path        varchar(255) null,
    banner_url         varchar(255) null,
    mobile_banner_path varchar(255) null,
    mobile_banner_url  varchar(255) null,
    bio                varchar(255) null,
    front_path         varchar(255) null,
    front_url          varchar(255) null,
    title              varchar(255) null,
    constraint UK_a3510lsxyycp2ygw47k8hjkyn
        unique (title)
);
