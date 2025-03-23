package com.example.petcare.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.petcare.model.ScheduleModel;
import com.example.petcare.model.UserModel;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserModel> __insertionAdapterOfUserModel;

  private final EntityInsertionAdapter<ScheduleModel> __insertionAdapterOfScheduleModel;

  private final EntityDeletionOrUpdateAdapter<ScheduleModel> __deletionAdapterOfScheduleModel;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserModel = new EntityInsertionAdapter<UserModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `user` (`email`,`password`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserModel entity) {
        statement.bindString(1, entity.getEmail());
        if (entity.getPassword() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPassword());
        }
      }
    };
    this.__insertionAdapterOfScheduleModel = new EntityInsertionAdapter<ScheduleModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `schedule` (`id`,`title`,`date`,`desc`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScheduleModel entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDate() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDate());
        }
        if (entity.getDesc() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDesc());
        }
      }
    };
    this.__deletionAdapterOfScheduleModel = new EntityDeletionOrUpdateAdapter<ScheduleModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `schedule` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScheduleModel entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertUser(final UserModel user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserModel.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSchedule(final ScheduleModel schedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfScheduleModel.insert(schedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSchedule(final ScheduleModel schedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfScheduleModel.handle(schedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserByEmail(final String email,
      final Continuation<? super UserModel> $completion) {
    final String _sql = "SELECT * FROM user WHERE email = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserModel>() {
      @Override
      @NonNull
      public UserModel call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final UserModel _result;
          if (_cursor.moveToFirst()) {
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            _result = new UserModel(_tmpEmail,_tmpPassword);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllSchedule(final Continuation<? super List<ScheduleModel>> $completion) {
    final String _sql = "SELECT * FROM schedule";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScheduleModel>>() {
      @Override
      @NonNull
      public List<ScheduleModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
          final List<ScheduleModel> _result = new ArrayList<ScheduleModel>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScheduleModel _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpDesc;
            if (_cursor.isNull(_cursorIndexOfDesc)) {
              _tmpDesc = null;
            } else {
              _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
            }
            _item = new ScheduleModel(_tmpId,_tmpTitle,_tmpDate,_tmpDesc);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
