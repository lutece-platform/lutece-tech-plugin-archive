DROP TABLE IF EXISTS archive_item;

/*==============================================================*/
/* Table structure for table directory_directory				*/
/*==============================================================*/
CREATE TABLE archive_item (
  
	id_archive_item INT DEFAULT 0 NOT NULL,
	folder_to_archive VARCHAR(255) DEFAULT NULL,
	archive_destination VARCHAR(255) DEFAULT NULL,
	archive_name VARCHAR(255) DEFAULT NULL,
	archive_type VARCHAR(50) DEFAULT NULL,
	archive_mime_type VARCHAR(50) DEFAULT NULL,
	state VARCHAR(20) DEFAULT NULL,	
	PRIMARY KEY  (id_archive_item)
  );

CREATE INDEX archive_item_state_idx ON archive_item (state);
