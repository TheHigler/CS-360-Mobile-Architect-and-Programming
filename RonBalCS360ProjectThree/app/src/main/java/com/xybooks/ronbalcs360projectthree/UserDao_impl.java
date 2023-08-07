package com.xybooks.ronbalcs360projectthree;

import android.annotation.SuppressLint;
import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation"})
public final class UserDao_impl implements UserDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

    private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

    @SuppressLint("RestrictedApi")
    public UserDao_impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR ABORT INTO `User` (`uid`,`username`,`password`) VALUES (?,?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, User value) {
                stmt.bindLong(1, value.uid);
                if (value.userName == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.userName);
                }
                if (value.password == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.password);
                }
            }
        };
        this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
            @Override
            public String createQuery() {
                return "DELETE FROM `User` WHERE `uid` = ?";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, User value) {
                stmt.bindLong(1, value.uid);
            }
        };
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void insertAll(final User... users) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __insertionAdapterOfUser.insert(users);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void delete(final User user) {
        __db.assertNotSuspendingTransaction();
        __db.beginTransaction();
        try {
            __deletionAdapterOfUser.handle(user);
            __db.setTransactionSuccessful();
        } finally {
            __db.endTransaction();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public List<User> getAll() {
        final String _sql = "SELECT * FROM user";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        __db.assertNotSuspendingTransaction();
        try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
            final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
            final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
            final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
            final List<User> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                final User _item;
                _item = new User();
                _item.uid = _cursor.getInt(_cursorIndexOfUid);
                _item.userName = _cursor.getString(_cursorIndexOfUserName);
                _item.password = _cursor.getString(_cursorIndexOfPassword);
                _result.add(_item);
            }
            return _result;
        } finally {
            _statement.release();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public List<User> loadAllByIds(final int[] userIds) {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT ");
        _stringBuilder.append("*");
        _stringBuilder.append(" FROM user WHERE uid IN (");
        final int _inputSize = userIds.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _inputSize);
        int _argIndex = 1;
        for (int _item : userIds) {
            _statement.bindLong(_argIndex, _item);
            _argIndex ++;
        }
        __db.assertNotSuspendingTransaction();
        try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
            final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
            final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
            final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
            final List<User> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                final User _item_1;
                _item_1 = new User();
                _item_1.uid = _cursor.getInt(_cursorIndexOfUid);
                _item_1.userName = _cursor.getString(_cursorIndexOfUserName);
                _item_1.password = _cursor.getString(_cursorIndexOfPassword);
                _result.add(_item_1);
            }
            return _result;
        } finally {
            _statement.release();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public User findByName(final String first, final String last) {
        final String _sql = "SELECT * FROM user WHERE username LIKE ? AND password LIKE ? LIMIT 1";
        @SuppressLint("RestrictedApi") final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
        int _argIndex = 1;
        if (first == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, first);
        }
        _argIndex = 2;
        if (last == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, last);
        }
        __db.assertNotSuspendingTransaction();
        try (Cursor _cursor = DBUtil.query(__db, _statement, false, null)) {
            final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
            final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
            final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
            final User _result;
            if (_cursor.moveToFirst()) {
                _result = new User();
                _result.uid = _cursor.getInt(_cursorIndexOfUid);
                _result.userName = _cursor.getString(_cursorIndexOfUserName);
                _result.password = _cursor.getString(_cursorIndexOfPassword);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _statement.release();
        }
    }
}
