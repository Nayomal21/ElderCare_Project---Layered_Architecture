package lk.ijse.dao;


import lk.ijse.dao.impl.*;

public class DAOFactory {

private  static  DAOFactory daoFactory;


    public enum DAOTYPES {
        BILL,ELDER,EMPLOYEE,FEE,GUARDIAN,QUERY,
        ITEM,MEAL,MEALITEM,PAYMENT,USER

    }

    private DAOFactory(){
    }

    public  static   DAOFactory getDAOFactory(){

        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;


    }

    public SuperDAO getDAO(DAOTYPES type) {
        switch (type) {
            case BILL:
                return new BillDAOImpl();
            case ELDER:
                return new ElderDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case FEE:
                return new FeeDAOImpl();
            case GUARDIAN:
                return new GuardianDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case MEAL:
                return new MealDAOImpl();
            case MEALITEM:
                return new MealItemDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }


}
