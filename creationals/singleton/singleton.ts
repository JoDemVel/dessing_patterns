(() => {
    class AppConfig {
        private static instance: AppConfig;
        private _config: any = {};

        private constructor() {
        }

        public static getInstance(): AppConfig {
            if (!AppConfig.instance) {
                AppConfig.instance = new AppConfig();
            }
            return AppConfig.instance;
        }

        public setConfig(key: string, value: any) {
            this._config[key] = value;
        }

        public getConfig(key: string): any {
            return this._config[key];
        }

        public getConfigs(): any {
            return this._config;
        }
    }

    const config = AppConfig.getInstance();
    config.setConfig('name', 'John Doe');
    config.setConfig('age', 30);
    console.log(config.getConfigs());

    const config2 = AppConfig.getInstance();
    console.log(config2.getConfigs());
})();