alter table tbl_item drop column gray_image_path;
alter table tbl_item drop column gray_image_url;

alter table tbl_collection drop constraint FKroh0s06dnxqg3e1qr49rj9rcw;
alter table tbl_collection drop column user_id;

alter table tbl_collection_item drop column status;
alter table tbl_collection_item drop constraint FK89rqbuivn2crmiskxb1pas0qn;
alter table tbl_collection_item drop column item_id;

alter table tbl_collection_item add column item_image_url varchar(255) null;
alter table tbl_collection_item add column item_gray_image_url varchar(255) null;
alter table tbl_collection_item add column item_en_name varchar(255) null;
alter table tbl_collection_item add column category varchar(255) null;