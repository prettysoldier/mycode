package java_.util.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 16:01
 **/
public class AnimalCatGarfield {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Garfield> garfields = new ArrayList<>();

        animals.add(new Animal());
        cats.add(new Cat());
        garfields.add(new Garfield());

//        List<? extends Cat> extendsCatFromAnimal = animals;
        List<? extends Cat> extendsCatFromCat = cats;
        List<? extends Cat> extendsCatFromGarfield = garfields;

        List<? super Cat> superCatFromAnimal = animals;
        List<? super Cat> superCatFromCat = cats;
//        List<? super Cat> superCatFromGarfield = garfields;

//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());

//        superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        Cat cat = extendsCatFromCat.get(0);
        System.out.println(cat);
        Cat cat1 =extendsCatFromGarfield.get(0);
//        Garfield garfield =extendsCatFromGarfield.get(0);
        // 类型缺失
        Object obj = superCatFromCat.get(0);
    }
}

class Animal{}
class Cat extends Animal{}
class Garfield extends Cat{}