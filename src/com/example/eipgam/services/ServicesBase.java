package com.example.eipgam.services;

import java.util.List;

import com.example.eipgam.helpers.data.EntityBase;
import com.example.eipgam.helpers.data.RepositoryBase;

public class ServicesBase<T extends EntityBase> {

	private RepositoryBase<T> repository;
	
	protected RepositoryBase<T>  GetRepository(){
		return repository;
	}
	
	public ServicesBase(Class<T> type) {
		
		repository = new RepositoryBase<T>(type);
	}
	
	public int saveOrUpdate(T entity) {
	
		return repository.saveOrUpdate(entity);
	}
	
	public  int  delete(T entity) {
		
		return repository.delete(entity);
	}
	public int delete(int id) {
		
		
		return repository.delete(id);
	}
	public int delete(long id) {
		
		
		return repository.delete((int)id);
	}	
	public List<T> retrieveAll() {
		
		return repository.getAll();
	}
}
