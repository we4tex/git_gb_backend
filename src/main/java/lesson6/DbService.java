package lesson6;

import lesson6.dao.CategoriesMapper;
import lesson6.dao.ProductsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbService {

    private final SqlSessionFactory sessionFactory;
    private final CategoriesMapper categoriesMapper;
    private final ProductsMapper productsMapper;
    private SqlSession session;

    public DbService() {
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(getClass().getResourceAsStream("myBatisConfig.xml"));
        openSession();
        categoriesMapper = session.getMapper(CategoriesMapper.class);
        productsMapper = session.getMapper(ProductsMapper.class);
    }

    public void openSession() {
        session = sessionFactory.openSession();
    }

    public void closeSession() {
        session.close();
    }

    public CategoriesMapper getCategoriesMapper() {
        return categoriesMapper;
    }

    public ProductsMapper getProductsMapper() {
        return productsMapper;
    }

    public void commitSession() {
        session.commit();
    }
}
