import { useForm } from "react-hook-form";
import { registerFormSchema, RegisterSchema } from "../schema/register-schema";

import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { useRegister } from "../hooks/use-register";


/**
 * Component for user registration.
 * 
 * This component renders a form with fields for the user's first name, last name, username, password, and confirmation of password.
 * It utilizes the Tanstack React Query `useRegister` hook to make a POST request to the "/auth/register" endpoint.
 * If the submission is successful, the form is reset.
 * If the submission fails, the error message is displayed below the submit button.
 * 
 * @returns The react component for user registration.
 */
export function RegisterForm() {
  const { mutate: register, isPending } = useRegister();

  // 1. Define your form.
  const form = useForm<RegisterSchema>({
    resolver: zodResolver(registerFormSchema),
    defaultValues: {
      firstName: "",
      lastName: "",
      username: "",
      password: "",
      confirmPassword: "",
    },
  });

  function onSubmit(values: RegisterSchema) {
    if (values.confirmPassword !== values.password) {
      form.setError("confirmPassword", {
        message: "Passwords do not match.",
      });
      return;
    }

    register(values);
    form.reset();
  }

  return (
    <div>
      <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
      <FormField
          control={form.control}
          name="firstName"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input type="text" placeholder="First Name" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="lastName"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input type="text" placeholder="Last Name" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="username"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input type="text" placeholder="Username" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input type="password" placeholder="Password" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="confirmPassword"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input
                  type="password"
                  placeholder="Confirm password"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <Button type="submit" disabled={isPending}>
          Register
        </Button>
      </form>
    </Form>
    </div>
    
  );
}
