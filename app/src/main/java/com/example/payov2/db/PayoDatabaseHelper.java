package com.example.payov2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.sql.Date;

public class PayoDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Payo.db";
    private static final int DATABASE_VERSION = 1;

    private static  final String TABLE_CLIENTS = "Clients";
    private static  final String COLUMN_ID = "Id";
    private static  final String COLUMN_FullName = "FullName";
    private static  final String COLUMN_Identification = "Identification";
    private static  final String COLUMN_Phone = "Phone";
    private static  final String COLUMN_HomePhone = "HomePhone";
    private static  final String COLUMN_Mail = "Mail";
    private static  final String COLUMN_Direction = "Direction";
    private static  final String COLUMN_RefFullName = "RefFullName";
    private static  final String COLUMN_RefPhone = "RefPhone";
    private static  final String COLUMN_RefDirection = "RefDirection";

    private static final String TABLE_LOANS = "Loans";
    private static  final String COLUMN_ID_Loans = "Loan_Id";
    private static final String COLUMN_ClientName = "ClientName";
    private static final String COLUMN_ClientDirection = "ClientDirection";
    private static final String COLUMN_PaymentFrecuency = "PaymentFrecuency";
    private static final String COLUMN_StartDate = "StartDate";
    private static final String COLUMN_DueDate = "DueDate";
    private static final String COLUMN_Capital = "Capital";
    private static final String COLUMN_ApplyInterestTo = "ApplyInterestTo";
    private static final String COLUMN_Interest = "Interest";
    private static final String COLUMN_NumberOfPayments = "NumberOfPayments";
    private static final String COLUMN_ApplyInterestForDelay = "ApplyInterestForDelay";
    private static final String COLUMN_InterestForDelay = "InterestForDelay";
    private static final String COLUMN_GraceDays = "GraceDays";
    private static final String COLUMN_Total = "Total";
    private static final String COLUMN_QuotaValue = "QuotaValue";

    public PayoDatabaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableClients = "CREATE TABLE " + TABLE_CLIENTS +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FullName + " TEXT, " +
                COLUMN_Identification + " TEXT, " +
                COLUMN_Phone + " TEXT, " +
                COLUMN_HomePhone + " TEXT, " +
                COLUMN_Mail + " TEXT, " +
                COLUMN_Direction + " TEXT, " +
                COLUMN_RefFullName + " TEXT, " +
                COLUMN_RefPhone + " TEXT, " +
                COLUMN_RefDirection + " TEXT);";

        String CreateTableLoans = "CREATE TABLE " + TABLE_LOANS +
                " (" + COLUMN_ID_Loans + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ClientName + " TEXT, " +
                COLUMN_ClientDirection + " TEXT, " +
                COLUMN_PaymentFrecuency + " TEXT, " +
                COLUMN_StartDate + " DATE, " +
                COLUMN_DueDate + " DATE, " +
                COLUMN_Capital + " DOUBLE, " +
                COLUMN_ApplyInterestTo + " TEXT, " +
                COLUMN_Interest + " INTEGER, " +
                COLUMN_NumberOfPayments + " INTEGER, " +
                COLUMN_ApplyInterestForDelay + " BOOL, " +
                COLUMN_InterestForDelay + " INTEGER, " +
                COLUMN_GraceDays + " INTEGER, " +
                COLUMN_Total + " DOUBLE, " +
                COLUMN_QuotaValue + " DOUBLE);";

        db.execSQL(CreateTableClients);
        db.execSQL(CreateTableLoans);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);
        onCreate(db);
    }

    public void addClient(String fullName, String identification, String phone, String homePhone, String mail, String direction, String refFullName, String refPhone,
                   String refDirection)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FullName, fullName);
        cv.put(COLUMN_Identification, identification);
        cv.put(COLUMN_Phone, phone);
        cv.put(COLUMN_HomePhone, homePhone);
        cv.put(COLUMN_Mail, mail);
        cv.put(COLUMN_Direction, direction);
        cv.put(COLUMN_RefFullName, refFullName);
        cv.put(COLUMN_RefPhone, refPhone);
        cv.put(COLUMN_RefDirection, refDirection);
        long result = db.insert(TABLE_CLIENTS, null, cv);

        if(result == -1)
        {
            Toast.makeText(context, "No se pudo registrar el cliente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void addLoan(String clientName, String clientDirection, String paymentFrecuency, String startDate, String dueDate, String capital, String applyInterestTo, String interest,
                          String numberOfPayments, String applyInterestForDelay, String interestForDelay, String graceDays, String total, String quotaValue)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ClientName, clientName);
        cv.put(COLUMN_ClientDirection, clientDirection);
        cv.put(COLUMN_PaymentFrecuency, paymentFrecuency);
        cv.put(COLUMN_StartDate, startDate);
        cv.put(COLUMN_DueDate, dueDate);
        cv.put(COLUMN_Capital, capital);
        cv.put(COLUMN_ApplyInterestTo, applyInterestTo);
        cv.put(COLUMN_Interest, interest);
        cv.put(COLUMN_NumberOfPayments, numberOfPayments);
        cv.put(COLUMN_ApplyInterestForDelay, applyInterestForDelay);
        cv.put(COLUMN_InterestForDelay, interestForDelay);
        cv.put(COLUMN_GraceDays, graceDays);
        cv.put(COLUMN_Total, total);
        cv.put(COLUMN_QuotaValue, quotaValue);
        long result = db.insert(TABLE_LOANS, null, cv);

        if(result == -1)
        {
            Toast.makeText(context, "No se pudo registrar el préstamo", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Préstamo registrado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData()
    {
        String readAllDataQuery = "SELECT * FROM " + TABLE_CLIENTS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(readAllDataQuery, null);
        }
        return cursor;
    }
    public Cursor readAllDataLoans()
    {
        String readAllDataQuery = "SELECT * FROM " + TABLE_LOANS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(readAllDataQuery, null);
        }
        return cursor;
    }

    public void updateData(String id, String fullName, String identification, String phone, String homePhone, String mail, String direction, String refFullName, String refPhone, String refDirection)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FullName, fullName);
        cv.put(COLUMN_Identification, identification);
        cv.put(COLUMN_Phone, phone);
        cv.put(COLUMN_HomePhone, homePhone);
        cv.put(COLUMN_Mail, mail);
        cv.put(COLUMN_Direction, direction);
        cv.put(COLUMN_RefFullName, refFullName);
        cv.put(COLUMN_RefPhone, refPhone);
        cv.put(COLUMN_RefDirection, refDirection);

        long result = db.update(TABLE_CLIENTS, cv, "Id=?", new String[]{id});
        if(result == -1)
        {
            Toast.makeText(context, "No se pudo actualizar el cliente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Cliente actualizado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLoan(String loanId, String clientName, String clientDirection, String paymentFrecuency, String startDate, String dueDate, String capital, String applyInterestTo, String interest,
                           String numberOfPayments, String applyInterestForDelay, String interestForDelay, String graceDays, String total, String quotaValue)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ClientName, clientName);
        cv.put(COLUMN_ClientDirection, clientDirection);
        cv.put(COLUMN_PaymentFrecuency, paymentFrecuency);
        cv.put(COLUMN_StartDate, startDate);
        cv.put(COLUMN_DueDate, dueDate);
        cv.put(COLUMN_Capital, capital);
        cv.put(COLUMN_ApplyInterestTo, applyInterestTo);
        cv.put(COLUMN_Interest, interest);
        cv.put(COLUMN_NumberOfPayments, numberOfPayments);
        cv.put(COLUMN_ApplyInterestForDelay, applyInterestForDelay);
        cv.put(COLUMN_InterestForDelay, interestForDelay);
        cv.put(COLUMN_GraceDays, graceDays);
        cv.put(COLUMN_Total, total);
        cv.put(COLUMN_QuotaValue, quotaValue);

        long result = db.update(TABLE_LOANS, cv, "Loan_Id=?", new String[]{loanId});
        if(result == -1)
        {
            Toast.makeText(context, "No se pudo actualizar el préstamo", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Préstamo actualizado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CLIENTS, "Id=?", new String[]{id});
        if(result == -1)
        {
            Toast.makeText(context, "No se pudo eliminar el cliente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Cliente eliminado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CLIENTS);
    }

}
