create table if not exists tbl_user
(
    user_id      binary(16)   not null
        primary key,
    email        varchar(255) null,
    name         varchar(255) null,
    phone_number varchar(255) null,
    path         varchar(255) null,
    url          varchar(255) null,
    role_type    varchar(255) null
);

create table if not exists tbl_product
(
    product_id     binary(16)   not null
        primary key,
    back_path      varchar(255) null,
    back_url       varchar(255) null,
    front_path     varchar(255) null,
    front_url      varchar(255) null,
    bio            varchar(255) null,
    product_status varchar(255) null,
    title          varchar(255) null,
    constraint UK_cou7p71iu1bfkbxq7adatkhm7
        unique (title)
);

create table if not exists tbl_product_banner_image
(
    tbl_product_banner_image_id binary(16)   not null
        primary key,
    ordering                    int          not null,
    path                        varchar(255) null,
    url                         varchar(255) null,
    product_id                  binary(16)   null,
    constraint FKtnklxk23ndsqqpbsbx7b0cky6
        foreign key (product_id) references tbl_product (product_id)
);

create table if not exists tbl_item
(
    item_id     binary(16)   not null
        primary key,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    item_bio    varchar(255) null,
    item_status varchar(255) null,
    limit_type  varchar(255) null,
    en_name     varchar(255) null,
    ko_name     varchar(255) null,
    price       int          null,
    quantity    int          null,
    product_id  binary(16)   null,
    constraint UK_fwtb92bsdey9v6l7f21kpd4we
        unique (ko_name),
    constraint UK_ivjhccb30o5qqi4qff1y1ng3b
        unique (en_name),
    constraint FKsf84ufa6fltpskcbqft9e7nm3
        foreign key (product_id) references tbl_product (product_id)
);

create table if not exists tbl_item_option_group
(
    item_option_group_id binary(16)   not null
        primary key,
    created_at           datetime(6)  null,
    updated_at           datetime(6)  null,
    name                 varchar(255) null,
    ordering             int          not null,
    item_id              binary(16)   null,
    constraint FKhlupffolfs7ctdeikvriveu0f
        foreign key (item_id) references tbl_item (item_id)
);

create table if not exists tbl_item_option
(
    item_option_id       binary(16)   not null
        primary key,
    created_at           datetime(6)  null,
    updated_at           datetime(6)  null,
    name                 varchar(255) null,
    price                int          null,
    ordering             int          not null,
    item_option_group_id binary(16)   null,
    constraint FK36xwufr7uhgrox1sv6o9k2kt4
        foreign key (item_option_group_id) references tbl_item_option_group (item_option_group_id)
);

create table if not exists tbl_item_image
(
    item_image binary(16)   not null
        primary key,
    ordering   int          not null,
    path       varchar(255) null,
    url        varchar(255) null,
    item_id    binary(16)   null,
    constraint FKcu395ch9q5kde0eh66stykdaq
        foreign key (item_id) references tbl_item (item_id)
);

create table if not exists tbl_delivery
(
    delivery_id     binary(16)   not null
        primary key,
    address         varchar(255) null,
    extra_address   varchar(255) null,
    zip_code        varchar(255) null,
    delivery_status varchar(255) null,
    is_basic        bit          not null,
    message         varchar(255) null,
    receiver_name   varchar(255) null,
    receiver_phone  varchar(255) null,
    user_id         binary(16)   null,
    constraint FKns1imq6p94y1k30ea3ic1fhg
        foreign key (user_id) references tbl_user (user_id)
);

create table if not exists tbl_community
(
    community_id   binary(16)   not null
        primary key,
    created_at     datetime(6)  null,
    updated_at     datetime(6)  null,
    path           varchar(255) null,
    url            varchar(255) null,
    community_type varchar(255) null,
    content        text         null,
    title          varchar(255) null,
    view           bigint       null,
    user_id        binary(16)   null,
    constraint FK33bjjy7f3bktqbju3d9l0y4m5
        foreign key (user_id) references tbl_user (user_id)
);

create table if not exists tbl_community_like
(
    community_like_id binary(16) not null
        primary key,
    community_id      binary(16) null,
    user_id           binary(16) null,
    constraint FKbmmscq4lstaw7caualaqpxo7
        foreign key (community_id) references tbl_community (community_id),
    constraint FKk85erur8eo2s7jdttqlqjhhk8
        foreign key (user_id) references tbl_user (user_id)
);

create table if not exists tbl_cart
(
    cart_id    binary(16)  not null
        primary key,
    created_at datetime(6) null,
    updated_at datetime(6) null,
    user_id    binary(16)  null,
    constraint FKhv6grtjnmtoylt2yyt4wmqtf3
        foreign key (user_id) references tbl_user (user_id)
);

create table if not exists tbl_cart_item
(
    cart_item_id binary(16)   not null
        primary key,
    amount       int          not null,
    image_url    varchar(255) null,
    name         varchar(255) null,
    price        int          not null,
    selected     bit          not null,
    cart_id      binary(16)   null,
    item_id      binary(16)   null,
    constraint FKc3i8mij3u7js7uyoksyn2to5n
        foreign key (item_id) references tbl_item (item_id),
    constraint FKhaw0aw4g8s9icxekpl4oi715a
        foreign key (cart_id) references tbl_cart (cart_id)
);

create table if not exists tbl_order
(
    order_id       binary(16)   not null
        primary key,
    created_at     datetime(6)  null,
    updated_at     datetime(6)  null,
    order_status   varchar(255) null,
    payment_method varchar(255) null,
    total_price    int          not null,
    delivery_id    binary(16)   null,
    user_id        binary(16)   null,
    constraint FKhyolniflkctr0p6bp4t8me9vj
        foreign key (user_id) references tbl_user (user_id),
    constraint FKmp5i1fhaoti1qnsbbd0whh3ir
        foreign key (delivery_id) references tbl_delivery (delivery_id)
);

create table if not exists tbl_order_item
(
    order_item_id binary(16)   not null
        primary key,
    amount        int          not null,
    image_url     varchar(255) null,
    name          varchar(255) null,
    price         int          not null,
    item_id       binary(16)   null,
    order_id      binary(16)   null,
    constraint FKmkqpajkg6p2wq4owcv1v08pc5
        foreign key (order_id) references tbl_order (order_id),
    constraint FKqrkrc5o23ar4cx1hiont8myy
        foreign key (item_id) references tbl_item (item_id)
);

create table if not exists tbl_video
(
    video_id  binary(16)   not null
        primary key,
    path      varchar(255) null,
    url       varchar(255) null,
    item_name varchar(255) null,
    video_url varchar(255) null
);