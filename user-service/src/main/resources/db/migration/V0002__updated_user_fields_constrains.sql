ALTER TABLE user_account
    ALTER COLUMN email TYPE VARCHAR(50) USING (email::VARCHAR(50));

ALTER TABLE user_account
    ALTER COLUMN email SET NOT NULL;

ALTER TABLE user_account
    ALTER COLUMN password TYPE VARCHAR(50) USING (password::VARCHAR(50));
