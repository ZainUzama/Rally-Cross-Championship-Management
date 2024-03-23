import random
from datetime import date

def menu():
    print("          ")
    print("              WORLD RALLY CROSS CHAMPIONSHIP        ")
    print("------------------------------------------------------------------------------------------------------------")

    print("     Type ADD for adding driver details.")
    print("     Type DDD for deleting")
    print("     Type UDD for updating driver details")
    print("     Type VCT for viewing the rally cross standing table")
    print("     Type SRR for simulating a random race")
    print("     Type VRL for viewing race table sorted according to the date")
    print("     Type STF to save the current data to a text file")
    print("     Type RFF to load data from the saved text file")
    print("     Type ESC to exit the program")

    print("------------------------------------------------------------------------------------------------------------")

driver_details = []
def add():
    l1 = []
    while True:
        try:
            name = input("Enter the racer's full name: ")
            if not name.isalpha():
                raise ValueError
        except ValueError:
            print("Invalid name. Enter a valid name. Name can be only contain alphabets.")
            continue
        driver_status = False
        for i in range(len(driver_details)):
            if driver_details[i][0] == name:
                driver_status = True
        if  driver_status == True:
            print("Driver already exist.")
            continue
        else:
            l1.append(name)
            break

    while True:
         try:
            age = int(input("Enter the age: "))
            if age <18:
                print("Invalid input. Driver age should be above 18. ")
                continue
            elif age >55:
                print("Invalid input. Driver age should be below 55.")
                continue
         except ValueError:
            print("Invalid input. Driver age should be a number.")
            continue
         l1.append(age)
         break

    while True:
        team = input("Enter the team: ")
        if team == "":
            print("Team name cannot be empty.")
            continue
        else:
            l1.append(team)
            break

    while True:
        car = input("Enter the car: ")
        if car == "":
            print("Car name cannot be empty.")
            continue
        else:
            l1.append(car)
            break

    while True:
        try:
            points = int(input("Enter the current points: "))
            if points < 0:
                print("Current points cannot be negative.")
                continue
        except ValueError:
            print("Enter valid points. Point should be a number.")
            continue
        l1.append(points)
        break

    driver_details.append(l1)
    # print(driver_details)

def ddd():
    d_name = input("Enter the driver name you need to delete: ")
    delete_index = -1
    for i in range(len(driver_details)):
        if driver_details[i][0] == d_name:
            delete_index = i
    if delete_index == -1:
        print("Invalid input "+d_name+" is unavailable.")
    else:
        del  driver_details[delete_index]
        print("successfully deleted.")

def udd():
    d_name = input("Enter the driver name you need to update: ")
    update_index = -1 #to access the [i] index value out of the loop
    for i in range(len(driver_details)):
        if driver_details[i][0] == d_name:
            update_index = i
    if update_index == -1:
        print(d_name, "driver is not found..")
    else:
        while True:
            try:
                name = input("Enter the racer's full name: ")
                if not name.isalpha():
                    raise ValueError
            except ValueError:
                print("Invalid name. Enter a valid name. Name can be only contain alphabets.")
                continue
            driver_status = False
            for i in range(len(driver_details)):
                if driver_details[i][0] == name:
                    driver_status = True
            if name == driver_details[update_index][0]:
                driver_status = False
            if driver_status == True:
                print("Driver already exist.")
                continue
            else:
                break

        while True:
            try:
                age = int(input("Enter the age: "))
                if age < 18:
                    print("Invalid input. Driver age should be above 18. ")
                    continue
                elif age > 55:
                    print("Invalid input. Driver age should be below 55.")
                    continue
            except ValueError:
                print("Invalid input. Drriver age should be a number.")
                continue
            break

        while True:
            team = input("Enter the team: ")
            if team == "":
                print("Team name cannot be empty.")
                continue
            else:
                break

        while True:
            car = input("Enter the car: ")
            if car == "":
                print("Car name cannot be empty.")
                continue
            else:
                break

        while True:
            try:
                points = int(input("Enter the current points: "))
                if points < 0:
                    print("Current points cannot be negative.")
                    continue
            except ValueError:
                print("Enter valid points. Point should be a number.")
                continue
            break

        driver_details[update_index][0] = name
        driver_details[update_index][1] = age
        driver_details[update_index][2] = team
        driver_details[update_index][3] = car
        driver_details[update_index][4] = points
        print("Successfully updated.")


def vct():
    t_list= []
    t_main_list= []
    for i in range(len(driver_details)):
        t_list.append(driver_details[i][4])
    t_list.sort(reverse=True)
    for x in t_list:
        temp_index = -1
        for y in range(len(driver_details)):
            if driver_details[y][4] == x:
                temp_index = y
        t_main_list.append(driver_details[temp_index])
        del driver_details[temp_index]
    for z in t_main_list:
        driver_details.append(z)
        print("Name:",z[0]," \tAge:",z[1]," \tTeam:",z[2],"\tCar:",z[3],"\tPoints:",z[4])


# write the driver details  on to the file
def stf():
    file = open("wrcc.txt", "w+")
    for i in driver_details:
        file.write(str(i[0])+","+str(i[1])+","+str(i[2])+","+str(i[3])+","+str(i[4])+"\n")
    file.close()
    print("data saved successfully.")


def srr():
    if len(driver_details) < 3:
        print("There are not enough drivers for a race.")
    else:
        location = ["Nyirád", "Höljes","Montalegre", "Barcelona", "Rīga", "Norway"]
        random.shuffle(driver_details)
        driver_details[0][4] = int(driver_details[0][4])+10
        driver_details[1][4] = int(driver_details[1][4])+7
        driver_details[2][4] = int(driver_details[2][4])+5
        random.shuffle(location)
        today = date.today() #get only todays date
        print("Date:"+ str(today) + " location:"+ location[0])
        print("First place:"+driver_details[0][0] + ",points:"+ str(driver_details[0][4]))
        print("second place:"+driver_details[1][0] + ",points:"+ str(driver_details[1][4]))
        print("Third place:"+driver_details[2][0] + ",points:"+ str(driver_details[2][4]))
        file = open("race details.txt","a", encoding="utf-8")
        file.write(str(today)+"/"+str(location[0])+"/"+str(driver_details)+"\n")
        file.close()
        print("Race generated sucessfully.")



def rff():
    file = open("wrcc.txt","r")
    while True:
        next_line = file.readline()
        if not next_line:
            break;
        driver_details.append(next_line.strip().split(","))
    file.close()
    print("Data successfully loaded.")


def vrl():
    file = open("race details.txt","r")
    while True:
        next_line = file.readline()
        if not next_line:
            break;
        print(next_line.strip().split("/"))
    file.close()


def user_option():
    while True:
        menu()
        user_preference = str(input("Enter the function you need to proceed :"))
        if user_preference.lower() == "esc":
            print("Thank you...")
            break
        elif user_preference.lower() == "add":
            add()
        elif user_preference.lower() == "ddd":
            ddd()
        elif user_preference.lower() == "udd":
            udd()
        elif user_preference.lower() == "vct":
            vct()
        elif user_preference.lower() == "stf":
            stf()
        elif user_preference.lower() == "rff":
            rff()
        elif user_preference.lower() == "vrl":
            vrl()
        elif user_preference.lower() == "srr":
            srr()
        else:
            print("Invalid input. Enter a proper function.")
            user_option()

user_option()














