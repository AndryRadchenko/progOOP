
import javax.persistence.*;
import java.util.*;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();

            addMeals();

            try {
                while (true) {
                    System.out.println("1: Meals within price range");
                    System.out.println("2: View discounted meals only");
                    System.out.println("3: Choose random meals up to 1kg");
                    System.out.println("4: View meals");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            viewMealsWithinPriceRange(sc);
                            break;
                        case "2":
                            viewDiscountedMealsOnly();
                            break;
                        case "3":
                            chooseRandomMealsUptoOneKilo();
                            break;
                        case "4":
                            viewMeals();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addMeals() {
        em.getTransaction().begin();
        try {
            List <Meal> meals = new ArrayList<>();
            meals.add(new Meal("Big Mac", 80, 330, 0));
            meals.add(new Meal("Big Tasty", 75, 300, 0));
            meals.add(new Meal("Double Cheeseburger", 70, 300, 10));
            meals.add(new Meal("Chicken Roll", 67, 290, 0));
            meals.add(new Meal("Cheeseburger", 50, 250, 10));
            meals.add(new Meal("Hamburger", 49, 220, 0));
            meals.add(new Meal("McChicken", 60, 200, 15));
            meals.add(new Meal("Fries", 20, 150, 0));
            meals.add(new Meal("Coke", 15, 200, 0));
            meals.add(new Meal("Orange juice", 20, 200, 0));

            for (Meal meal : meals){
                em.persist(meal);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewMealsWithinPriceRange(Scanner sc) {
        System.out.print("Enter priceFrom: ");
        String sFrom = sc.nextLine();
        int priceFrom = Integer.parseInt(sFrom);

        System.out.print("Enter priceTo: ");
        String sTo = sc.nextLine();
        int priceTo = Integer.parseInt(sTo);

        Query query = em.createQuery("SELECT c FROM Meal c WHERE c.price >= :priceFrom AND (c.price*(100 - c.discount)/100) <= :priceTo", Meal.class);
        query.setParameter("priceFrom", priceFrom);
        query.setParameter("priceTo", priceTo);
        List<Meal> meals = query.getResultList();

        for (Meal meal : meals){
            System.out.println(meal);
        }

    }

    private static void viewDiscountedMealsOnly() {
        Query query = em.createQuery("SELECT c FROM Meal c WHERE c.discount > 0", Meal.class);
        List<Meal> meals = query.getResultList();

        for (Meal meal : meals){
            System.out.println(meal);
        }
    }

    private static void chooseRandomMealsUptoOneKilo() {
        Random rnd = new Random();

        Query query = em.createQuery("SELECT c FROM Meal c", Meal.class);
        List<Meal> meals = query.getResultList();

        List <Meal> selectedMeals = new ArrayList<>();
        int overallWeight = 0;

        while (overallWeight < 1000){
            int i = rnd.nextInt(meals.size());

            if (overallWeight + meals.get(i).getWeight() >= 1000){
                break;
            } else {
                overallWeight += meals.get(i).getWeight();
                selectedMeals.add(meals.get(i));
            }
        }

        for (Meal meal : selectedMeals){
            System.out.println(meal);
        }
        System.out.println("TOTAL WEIGHT\t" + overallWeight + "g");
    }


    private static void viewMeals() {
        Query query = em.createQuery("SELECT c FROM Meal c", Meal.class);
        List<Meal> list = (List<Meal>) query.getResultList();

        for (Meal c : list)
            System.out.println(c);
    }
}


