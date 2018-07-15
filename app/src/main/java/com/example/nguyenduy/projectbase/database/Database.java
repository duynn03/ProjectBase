package com.example.nguyenduy.projectbase.database;

import android.content.Context;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Database {

    private static final String DATABASE_NAME_DEFAULT = "TestRealm";
    private static final int DATABASE_SCHEMA_VERSION_DEFAULT = 1;

    public static void configDefault(Context context) {
        Realm.init(context);
        // set configDefault default
        Realm.setDefaultConfiguration(getConfigDefault());
        // Realm realm = Realm.getDefaultInstance();
    }

    public void initDatabase() {
   /*     // getDefaultInstance sử dụng default RealmConfiguration
        Realm realm = Realm.getDefaultInstance();

        // get all dog > 2 age
        final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 2).findAll();
        puppies.size(); // => 0 because no dogs have been added to the Realm yet

        // Persist your data in a transaction
        realm.beginTransaction();
        final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
        Person person = realm.createObject(Person.class); // Create managed objects directly
        person.getDogs().add(managedDog);
        realm.commitTransaction();


        // Listeners will be notified when data changes
        puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> results, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                changeSet.getInsertions(); // => [0] is added.
            }
        });

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Dog dog = bgRealm.where(Dog.class).equalTo("age", 1).findFirst();
                dog.setAge(3);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Original queries and Realm objects are automatically updated.
                puppies.size(); // => 0 because there are no more puppies younger than 2 years old
                managedDog.getAge();   // => 3 the dogs age is updated
            }
        });*/

    }

    private static RealmConfiguration getConfigDefault() {
        return new RealmConfiguration.Builder()
                .name(DATABASE_NAME_DEFAULT + ".realm")
                .schemaVersion(DATABASE_SCHEMA_VERSION_DEFAULT)
                //.modules(new MyCustomSchema(), new MyOtherModule(), Realm.getDefaultModule())
                .build();
    }


}
