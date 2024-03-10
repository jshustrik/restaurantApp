Система управления заказами в ресторане
=====================
Краткое описание проекта
-----------------------------------
Данное приложение поддерживает два типа пользователей: посетителей и администраторов. 
Система обрабатывает заказы в многопоточном режиме, позволяя клиентам добавлять блюда в заказ в реальном времени, а также отображает статусы заказов. 
Администраторы могут управлять меню, добавляя или удаляя блюда.

Помимо этого реализован дополнительный функционал: 
- Все необходимые данные шифруются
- У клиентов есть возможность оставлять отзывы о блюдах после оплаты заказа. Отзывы влючают оценку от 1 до 5 и текстовый комментарий.
- Администратор может просматривать статистику по заказам и отзывам (самые популярные блюда, средняя оценка блюд, количество заказов за период).
- Система приоритетов для обработки заказов. Заказы приоритезированы по принципу "first in - first out". Количество заказов, которые могут выполняться одновременно, неограничено.

Все необходимые данные сохраняются в csv-файлы. Вообще говоря, это модифицированная и супер доработанная программа из 1 КДЗ (оттуда взято примерно 25%). В прошлый раз данные сериализовались, но сейчас я столкнулась со следующей проблемой - таймер нельзя сериализовать. Поэтому было решено прибегнуть к csv.

Все решения соблюдают принципы ООП и SOLID, а также использованы шаблоны проектирования, где это уместно.


Немного про шаблоны.
-----------------------------------

Было решено не использовать паттерн "Одиночка" (хотя кое-где это было уместно), потому что одновременно с тем, что он является паттерном, он нарушает один из принципов SOLID, а именно принцип единой ответственности.

Использован паттерн "Команда" для обработки команд пользователя.
Вместо огромного switch-case каждая команда завёрнута в собственный класс, а объект команды знает, какой команде и в каком виде передавать запросы.

Использован паттерн "Строитель" для создания пользователя - админа или посетителя. 

Как использовать проект
-----------------------------------

Данная программа рассчитана на многократный запуск, аналогично входу на сайт. Для удобства использования добавлен цикл while (true) в Maik.kt

Запуская проект, перед нами предстаёт три опции:

```
Continue as:
   1. Visitor
   2. Admin
   3. Exit
Your option: 1
```

Посетитель
----------------------------------
Рассмотрим, какие варианты использования есть у посетителя, нажав 1:

```
Login with current account or sign in:
   1. Login into account
   2. Sign in new account

Your option: 1

Enter UserName: meow
Enter password: meowmeow
```

По умолчанию создан один посетитель с логином и паролем, предоставленными выше, но при желании Вы можете зарегистрировать своего пользователя.

Появляется следующее меню:

```
Choose as option:
   1. Make order.
   2. Change order.
   3. Cancel order.
   4. Check orders.
   5. Pay for the order.
   6. Fill your wallet.
   7. Exit.
Your option: 1
```

Рассмотрим каждый пункт:

1. Сделать заказ

Появляется следующее меню:

```
Make your order:
   1. Add dish to order
   2. Finish order
   3. Exit
```

Первый пункт (`Добавить блюдо в заказ`) показывает список доступных блюд. По умолчанию я сделала следующие блюда: (их количество может отличаться, поскольку я уже запускала программу)

```
List of all available dishes:
1. Бличники with amount of 10. Price: 100
2. Курочка with amount of 5. Price: 450
3. Мяско with amount of 3. Price: 500
```

Чтобы добавить блюдо, необходимо ввести его номер. Если количество блюда равно нулю, то выдастся предупреждение, что необходимого количества блюда нет. При выборе недоступного блюда выдастся предупреждение, что такого блюда нет. Затем можно завершить заказ, либо же выйти из заказа и вернуться к его редактированию позже благодаря функции "Изменить заказ"

2. Редактировать заказ

```
Choose order to change: (input number)
1 meow order with price 100 has status: Creating
```

Сюда попадают все заказы, имеющие статус "Создаётся" или "В процессе". Пользователь может редактировать только свои заказы.

3. Отменить заказ

Выбрав эту опцию, можно отменить выбранный заказ.

4. Посмотреть статус заказа

Выбрав эту опцию, можно посмотреть статус заказа. Их всего 3 штуки:

- Creating - заказ на стадии создания
- In process... - заказ на стадии приготовления
- Finished - заказ готов. При этом, как только готовый заказ будет оплачен, он будет удален из списка

5. Оплатить заказ

```
Choose an order for payment
1 meow order with price 100

1
Success payment
Would you like to leave a review?
   1. Yes
   2. No
```

После оплаты заказа появляется возможность оставить отзыв:

```
Would you like to leave a review?
   1. Yes
   2. No
1
Rate the order from 1 to 5:
5
You can add commentary for your review: (input text or leave blank)
Я люблю блинчики!!!
```

6. Пополнить кошелёк

```
How much money to deposit? 
1000
You have transferred 1000 to your wallet
Now you have 1250
```

Раньше была функция "Посмотреть баланс", в этом КДЗ она убрана, поскольку включена в текущую.

Администратор
----------------------------------

Завершим сеанс посетителя и перейдём в администратора.

```
Choose as option:
   1. Make order.
   2. Change order.
   3. Cancel order.
   4. Check orders.
   5. Pay for the order.
   6. Fill your wallet.
   7. Exit.
Your option: 7

Continue as:
   1. Visitor
   2. Admin
   3. Exit
Your option: 2
```

По умолчанию создан администратор `admin` с паролем `admin`, при желании Вы можете создать своего.

```
Login with current account or sign in:
   1. Login into account
   2. Sign in new account

Your option: 1

Enter UserName: admin
Enter password: admin
```

Появится следующее меню:

```
Admin. Choose an option:
   1. Show information about dishes
   2. Add dish
   3. Change dish
   4. Remove dish
   5. Check income
   6. Get statistic
   7. Exit.
```

1. Показать информацию о блюдах

Увидим всю информацию о блюдах в формате `Name,Amount,Price,Difficulty`

2. Добавить блюдо

```
Enter you option: 2

Input the name of the dish: Тортик
Input amount of dish: 100
Input the price of the dish: 2000
Input the cooking time of the dish: (in seconds) 100
The dish has been successfully added!
```

3. Изменить блюдо

Здесь можно изменить количество, цену или время готовки любого доступного блюда.

```
Enter you option: 3

Choose dish to change
1 Бличники with amount 6, price 100 and difficulty 2
2 Курочка with amount 5, price 450 and difficulty 10
3 Мяско with amount 3, price 500 and difficulty 8
4 Тортик with amount 100, price 2000 and difficulty 100
4
What part you want to change?
   1. Change amount
   2. Change price
   3. Change cooking time
   4. Exit
1
Input new amount:
11
```

4. Удалить блюдо

```
Enter you option: 4

Choose dish to delete
1 Бличники with amount 6, price 100 and difficulty 2
2 Курочка with amount 5, price 450 and difficulty 10
3 Мяско with amount 3, price 500 and difficulty 8
4 Тортик with amount 11, price 2000 and difficulty 100
4
Deleted!
```

5. Проверить доход

Данный функционал выводит доход ресторана за всё время.

```
Enter you option: 5

Your current income is 100
```

6. Получить статистику

```
Enter you option: 6

What type of statistic you want?
   1. The number of orders
   2. Average order rating
   3. Most popular dish
   4. Exit
1
1
What type of statistic you want?
   1. The number of orders
   2. Average order rating
   3. Most popular dish
   4. Exit
2
5.0
What type of statistic you want?
   1. The number of orders
   2. Average order rating
   3. Most popular dish
   4. Exit
3
Бличники
```

Здесь доступно три типа статистики: количество заказов, средний рейтинг и наиболее популярное блюдо.

Немного слов от автора
-----------------------------------

Спасибо за внимание, скелетор вернётся позже с ещё одной домашкой!

По всем вопросам, предложениям и замечаниям просьба писать в тг — [@jshustrik](https://t.me/jshustrik)

![screenshot of sample](https://img.freepik.com/premium-photo/cat-computer_815088-234.jpg?size=338&ext=jpg&ga=GA1.1.1222169770.1702512000&semt=ais)
