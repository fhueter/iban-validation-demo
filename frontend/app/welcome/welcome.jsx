export function Welcome() {
  return (
    <main className="flex items-center justify-center pt-16 pb-4">
      <div className="flex-1 flex flex-col items-center gap-16 min-h-0">
        <header className="flex flex-col items-center gap-9">
          <h1>
            IBAN-Validation-Demo
          </h1>
        </header>
        <div className="max-w-[300px] w-full space-y-6 px-4">
          <nav className="rounded-3xl border border-gray-200 p-6 dark:border-gray-700 space-y-4">
            <p className="leading-6 text-gray-700 dark:text-gray-200 text-center">
              Was möchtest du tun?
            </p>
            <ul>
                <li>
                  <a
                    className="group flex items-center gap-3 self-stretch p-3 leading-normal text-blue-700 hover:underline dark:text-blue-500"
                    href=""
                  >
                    IBAN validieren
                  </a>
                </li>
				<li>
					<a
						className="group flex items-center gap-3 self-stretch p-3 leading-normal text-blue-700 hover:underline dark:text-blue-500"
						href=""
					>
						Bank hinzufügen
					</a>
				</li>
            </ul>
          </nav>
        </div>
      </div>
    </main>
  );
}
