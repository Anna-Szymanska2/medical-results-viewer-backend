# TELM_Szymańska_Pietrucha_Potwora

## Installation
To run telm-backend MySQL Server 8.4 is needed:
https://dev.mysql.com/downloads/mysql/

Configuration od database:

cd C:\Program Files\MySQL\MySQL Server 8.4\bin

mysql -u root -p

create database TELM;

create user 'telmuser'@'%' identified by 'pass';

grant all on TELM.* to 'telmuser'@'%';

Additionally, you'll need pixelmed.jar version 20240221:
is on our discord in database channel.

The file needs to be put into lib directory of the project.

You can now run the backend application.
After first run change in application.properties create to update.
Also comment initializeDB function in DatabaseInit.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.
pending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

