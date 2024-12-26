import { createLazyFileRoute } from "@tanstack/react-router";

export const Route = createLazyFileRoute("/_protected/dashboard")({
  component: RouteComponent,
});

function RouteComponent() {
  return <div>Hello "/_protected/dashboard"!</div>;
}