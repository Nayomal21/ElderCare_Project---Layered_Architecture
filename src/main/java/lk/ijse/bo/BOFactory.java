package lk.ijse.bo;


import lk.ijse.bo.impl.BIllBOImpl;
import lk.ijse.bo.impl.*;

public class BOFactory {
    public enum BOTYPES {

       BILL,DASHBOARDTASK,DASHBOARD,ELDERADD,ELDER,ELDERUPDATE,EMPLOYEE,EMPLOYEEADD,EMPLOYEEUPDATE,ITEMADD,
        ITEM,ITEMUPDATE,MEAL,MELAUPDATE,PAYMENT,REGISTER,REPORT,USER
    }

    private  static BOFactory BOFactory;




    private BOFactory(){
    }

    public  static BOFactory getBOFactory(){

        return BOFactory == null ? BOFactory= new BOFactory() : BOFactory;


    }

    public SuperBO getBO(BOTYPES type) {
        switch (type) {
            case BILL:
                 return new BIllBOImpl();
                
            case DASHBOARDTASK:
                 return new DashBoardTaskBOImpl();
                
            case DASHBOARD:
                 return new DashBordFormBOImpl();
                
            case ELDERADD:return new ElderAddBOImpl();
                
            case ELDER:
                 return new ElderBOImpl();
                
            case ELDERUPDATE:
                 return new ElderUpdateBOImpl();
                
            case EMPLOYEE:
                 return new EmployeeBOImpl();
                
            case EMPLOYEEADD:
                 return new EmployeeAddBOImpl();
                
            case EMPLOYEEUPDATE:
                 return new EmployeeUpdateBOImpl();
                
            case ITEMADD:
                 return new ItemAddBOImpl();
                
            case ITEM:
                return new ItemBOImpl();
            case ITEMUPDATE:
                 return new ItemUpdateBOImpl();
                
            case MEAL:
                 return new MealBOImpl();
                
            case MELAUPDATE:
                 return new MealUpdateBOImpl();
                
            case PAYMENT:
                 return new PaymentBOImpl();
                
            case REGISTER:
                 return new ReportBOImpl();
                
            case REPORT:
                 return new ReportBOImpl();
                
            case USER:
                 return new UserBOImpl();
                
            
            default:
                return null;
        }

    }

}