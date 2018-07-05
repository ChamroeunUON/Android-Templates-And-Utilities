package com.example.database.query;

import com.example.database.dao.ProductDAO;
import com.example.database.data.Data;

public class ProductDeleteQuery extends Query {
	private long mId;

	public ProductDeleteQuery(long id) {
		mId = id;
	}

	@Override
	public Data<Void> processData() {
		ProductDAO dao = new ProductDAO();
		dao.delete(mId);

		Data<Void> data = new Data<>();
		data.setDataObject(null);

		return data;
	}
}
