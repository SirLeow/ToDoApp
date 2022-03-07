package com.example.contactapp.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.databinding.ActivityAddBinding
import com.example.todoapp.recycler_view.Task

class HelperDB(
    context: Context?
) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object{
        private const val NOME_BANCO = "Contato.db"
        private const val VERSAO_ATUAL = 1
    }


    val TABLE_NAME = "tarefa"
    val COLUMNS_ID = "id"
    val COLUMNS_DESC = "descricao"
    val COLUMNS_DATA = "data"
    val COLUMNS_HORA = "hora"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE by lazy {
        "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_ID INTEGER NOT NULL," +
                "$COLUMNS_DESC TEXT NOT NULL," +
                "$COLUMNS_DATA TEXT NOT NULL," +
                "$COLUMNS_HORA TEXT NOT NULL," +
                "" +
                "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
                ")"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1 != p2){
            p0?.execSQL(DROP_TABLE)
        }
        onCreate(p0)
    }

    fun onSearch(busca: String) : List<Task>{
        val db = readableDatabase ?: return mutableListOf()
        val lista = mutableListOf<Task>()
        val sql = "SELECT $busca FROM $TABLE_NAME"
        val cursor = db.rawQuery(sql, null)?: return mutableListOf()
        while (cursor.moveToNext()){
            val task = Task(
                descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS_DESC)),
                data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS_DATA)),
                hora = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS_HORA))
            )
            lista.add(task)
        }
        cursor.close()
        return lista
    }

    fun onAdd(binding: ActivityAddBinding){
        val db = writableDatabase
        val sql = "INSERT INTO $TABLE_NAME ($COLUMNS_DESC, $COLUMNS_DATA, $COLUMNS_HORA) " +
                "VALUES ('${binding.tiDescricao.text}','${binding.tiData.text}','${binding.tiHora.text}')"
        db.execSQL(sql)
        db.close()
    }

    fun onDelete(){

    }
}