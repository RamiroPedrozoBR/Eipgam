package com.example.eipgam.helpers.data;

import java.io.IOException;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eipgam.models.Goal;
import com.example.eipgam.models.GoalType;
import com.example.eipgam.models.PayType;
import com.example.eipgam.models.Spending;
import com.example.eipgam.models.SpendingType;
import com.example.eipgam.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ProviderHelper extends OrmLiteSqliteOpenHelper {

	private static ProviderHelper current;

	public static ProviderHelper getCurrent(){
		return current;
	}

	public static void Initialize(Context context){

		current = new ProviderHelper(context); 
	}


	private static final String DATABASE_NAME = "eipgam.db";
	private static final int DATABASE_VERSION = 1;

	private Dao<User, String> userDao = null;
	private DatabaseInitializer initializer;



	private ProviderHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		initializer = new DatabaseInitializer(context);

		try {
			getInitializer().createDatabase();
			getInitializer().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(ProviderHelper.class.getName(), "onCreate");

			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, PayType.class);
			TableUtils.createTable(connectionSource, SpendingType.class);
			TableUtils.createTable(connectionSource, Spending.class);
			TableUtils.createTable(connectionSource, GoalType.class);
			TableUtils.createTable(connectionSource, Goal.class);

			createInitialsData();

		} catch (SQLException e) {
			Log.e(ProviderHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(ProviderHelper.class.getName(), "onUpgrade");

			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, PayType.class, true);
			TableUtils.dropTable(connectionSource, SpendingType.class, true);
			TableUtils.dropTable(connectionSource, Spending.class, true);
			TableUtils.dropTable(connectionSource, GoalType.class, true);
			TableUtils.dropTable(connectionSource, Goal.class, true);



			onCreate(db);
			
		} catch (SQLException e) {
			Log.e(ProviderHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}



		


	}

	private void createInitialsData(){
		try 
		{
			//Tipos de pagamento
			Dao<PayType, Integer> payDao = (Dao<PayType, Integer>) getGenericDao(PayType.class);
			PayType pay = new PayType();
			pay.setId(0);
			pay.setName("Paga");
			payDao.create(pay);
			
			pay = new PayType();
			pay.setId(1);
			pay.setName("Parcelado");
			payDao.create(pay);
			
			pay = new PayType();
			pay.setId(2);
			pay.setName("Mensal");
			payDao.create(pay);
			
			pay = new PayType();
			pay.setId(3);
			pay.setName("Anual");
			payDao.create(pay);
			
			//Tipos de gastos
			Dao<SpendingType, Integer> spendingTypeDao = (Dao<SpendingType, Integer>) getGenericDao(SpendingType.class);
			SpendingType spendingType = new SpendingType();
			spendingType.setName("Lazer");
			spendingTypeDao.create(spendingType);
			
			spendingType = new SpendingType();
			spendingType.setName("Saúde");
			spendingTypeDao.create(spendingType);
			//
			//Tipos de Metas
			Dao<GoalType, Integer> goalTypeDao = (Dao<GoalType, Integer>) getGenericDao(GoalType.class);
			GoalType goalType = new GoalType();
			goalType.setName("Compra");
			goalTypeDao.create(goalType);
			
			goalType = new GoalType();
			goalType.setName("Economia");
			goalTypeDao.create(goalType);
			//
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Dao<User, String> getUserDao() throws SQLException {
		if (userDao == null) {
			userDao =getDao(User.class);
		}
		return userDao;
	}
	public Dao<?, Integer> getGenericDao(Class<?> ent) throws SQLException {

		return getDao(ent);
	}


	@Override
	public void close() {
		super.close();
		//	userDao	 = null;

	}

	public DatabaseInitializer getInitializer() {
		return initializer;
	}


}