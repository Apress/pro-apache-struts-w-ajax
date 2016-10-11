

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
           ON waf.*
           TO waf_user@"%"
           IDENTIFIED BY 'password';
FLUSH PRIVILEGES;
