package com.dataart.vyakunin.gdg_wear;

import android.provider.BaseColumns;

import com.annotatedsql.annotation.provider.Provider;
import com.annotatedsql.annotation.provider.URI;
import com.annotatedsql.annotation.sql.Autoincrement;
import com.annotatedsql.annotation.sql.Column;
import com.annotatedsql.annotation.sql.PrimaryKey;
import com.annotatedsql.annotation.sql.Schema;
import com.annotatedsql.annotation.sql.Table;

/**
 * See https://github.com/hamsterksu/Android-AnnotatedSQL
 */
@Schema(className = "StoreSchema", dbName = "gdg_wear.db", dbVersion = 3)
@Provider(authority = "com.dataart.vyakunin.store", name = "StoreContentProvider", schemaClass = "StoreSchema")
public interface Store {

    @Table(ShortMessages.TABLE_NAME)
    public static interface ShortMessages {
        String TABLE_NAME = "short_messages";
        @URI
        String CONTENT_URI = "short_messages";

        @PrimaryKey
        @Autoincrement
        @Column(type = Column.Type.INTEGER)
        String ID = BaseColumns._ID;

        @Column(type = Column.Type.TEXT)
        String VALUE = "value";
    }

    @Table(ContactNumbers.TABLE_NAME)
    public static interface ContactNumbers {
        String TABLE_NAME = "contact_numbers";
        @URI
        String CONTENT_URI = "contact_numbers";

        @PrimaryKey
        @Autoincrement
        @Column(type = Column.Type.INTEGER)
        String ID = BaseColumns._ID;

        @Column(type = Column.Type.TEXT)
        String PHONE_NUMBER = "phone_number";
    }
}

