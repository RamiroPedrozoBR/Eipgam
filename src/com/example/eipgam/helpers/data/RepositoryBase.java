package com.example.eipgam.helpers.data;

import java.util.List;
import android.database.SQLException;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class RepositoryBase<T extends EntityBase>  {

	Dao<T, Integer> genericDao;

	public RepositoryBase(Class<T> type) 
	{
		try {
			genericDao = (Dao<T, Integer>) ProviderHelper.getCurrent().getGenericDao(type);
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int delete(int id)
	{
		try {
			return genericDao.deleteById(id);
		} 
		catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int delete(T entity)
	{
		try {
			return genericDao.delete(entity);
		} 
		catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public T getFirstBy(String propertyName,String value)
	{		
		try 
		{
			QueryBuilder<T, Integer> qb = genericDao.queryBuilder();

			qb.where().eq(propertyName, value);

			PreparedQuery<T> pq = qb.prepare();

			return genericDao.queryForFirst(pq);

		}
		catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public T getById(int id) 
	{		
		try {
			QueryBuilder<T, Integer> qb = genericDao.queryBuilder();

			qb.where().eq("id", id);

			PreparedQuery<T> pq = qb.prepare();
			return genericDao.queryForFirst(pq);

		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<T> getAll()
	{		
		try {
			return genericDao.queryForAll();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int saveOrUpdate(T entity) {

		try{
			if(entity.getId() == 0){
				return genericDao.create(entity);
			}

			else
				return genericDao.update(entity);

		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
