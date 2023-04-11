package br.com.ada.americanas.adamon.model;

public class TesteJBDC {
            public static void main(String[] args) {
                try {
                    Class.forName("org.postgresql.Driver");
                    System.out.println("PostgreSQL JDBC driver encontrado no classpath");
                } catch (ClassNotFoundException e) {
                    System.out.println("PostgreSQL JDBC driver não encontrado no classpath");
                }
            }
        }

