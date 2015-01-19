/****************************************************************************************************************************************/
/*-Autor: Gregoire BARRET																												*/
/*-Date: 19/03/2013																														*/
/*-Project: Social NetWort																												*/
/*-Class: IRepository, Interface																										*/
/*-Content: Interface of the data base managment with all methodes for manage the data base												*/
/****************************************************************************************************************************************/
package com.bflavien.contact.dao;

import java.util.List;

import android.database.Cursor;

public interface IRepository<T> {

    public List<T> GetAll();

    public T GetById(Long id);

    public long Save(T entite);

    public void Update(T entite);

    public void Delete(Long id);

    public List<T> ConvertCursorToListObject(Cursor c);

    public T ConvertCursorToObject(Cursor c);

    public T ConvertCursorToOneObject(Cursor c);
}
