    package com.apress.javaedge.common;

    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.Properties;
    /**
     * The DAOFactory is responsible for creating and returning an instance of a requested
     * DAO.  The developer can request a DAO by calling the getDAO() method and passing
     * in one of the constants defined below.
     *
     * The actual class that gets returned by the DAO is shown below.
     *
     * @author  John Carnell
     */
    public class DAOFactory {
        private static Properties classInfo = new Properties();  //Holds all of the dao Java class names
        private static DAOFactory daoFactory = null;             //Holds an instance of the dao factory.

        public static final String MEMBERDAO = "dao.member.impl"; //Lookup key for the MemberDAO
        public static final String STORYDAO  = "dao.story.impl";  //Lookup key for the StoryDAO

        /**
         *   Private constructor that will load all of the fully qualified DAO
         *   class names from the datatier.properties file.
         */
        private DAOFactory() {
            try{
                String daoFileName = System.getProperty("datatier.properties", "");

                classInfo.load(new FileInputStream(daoFileName));
            }
            catch(IOException e){
                System.out.println(e.toString());
            }
        }

        //Instantiate a new instance of the class and return it back to the
        static{
            daoFactory = new DAOFactory();
        }

        /**
         *  Returns a single instance of DAOFactory
         */
        public static DAOFactory getInstance(){
            return daoFactory;
        }

        /**
         *   The getDAO() method will retrieve a DAO requested by the user.
         */
        public DataAccessObject getDAO(String desiredDAO) throws DataAccessException{
            Object dao = null;

            //If the classInfo properties object contains the key requested by the user.
            if (classInfo.containsKey(desiredDAO)){

                try{
                    //Get the fully qualified class name
                    String className = (String) classInfo.get(desiredDAO);

                    //Retrieve a Class object for the requested className
                    Class desiredClass = Class.forName(className);

                    //Retrieve a new instance of the requested DAO
                    dao = desiredClass.newInstance();
                }
                catch(InstantiationException e){
                    throw new DataAccessException("InstantiationException " +
                    "in DAOFactory.getDAO(): " + desiredDAO,e);
                }
                catch(IllegalAccessException e){
                    throw new DataAccessException("IllegalAccessException " +
                    "in DAOFactory.getDAO(): " + desiredDAO,e);
                }
                catch(ClassNotFoundException e){
                    throw new DataAccessException("DataAccessException " +
                    "in DAOFactory.getDAO(): " + desiredDAO,e);
                }

                return (DataAccessObject) dao;
            }
            else{
                throw new DataAccessException("Unable to locate the DAO requested " +
                "in DAOFactory.getDAO(): " + desiredDAO);
            }
        }
    }



